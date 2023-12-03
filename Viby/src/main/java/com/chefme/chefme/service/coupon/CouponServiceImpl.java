package com.chefme.chefme.service.coupon;


import com.chefme.chefme.dto.coupon.CouponDTO;
import com.chefme.chefme.dto.coupon.CouponDTOMapper;
import com.chefme.chefme.exceptions.ResourceNotFoundException;
import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.coupons.Coupon;
import com.chefme.chefme.model.product.Product;
import com.chefme.chefme.model.user.UserEntity;
import com.chefme.chefme.repository.CouponRepository;
import com.chefme.chefme.responses.ResponseHandler;
import com.chefme.chefme.service.product.ProductService;
import com.chefme.chefme.service.user.UserEntityService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CouponServiceImpl  implements  CouponService{

    private final CouponRepository couponRepository;
    private  final CouponDTOMapper couponDTOMapper;
    private final ProductService productService;
    private final UserEntityService userEntityService;

    public CouponServiceImpl(CouponRepository couponRepository, CouponDTOMapper couponDTOMapper, ProductService productService, UserEntityService userEntityService) {
        this.couponRepository = couponRepository;
        this.couponDTOMapper = couponDTOMapper;
        this.productService = productService;
        this.userEntityService = userEntityService;
    }

    @Override
    public ResponseEntity<Object> addCoupon(@NotNull final UserDetails userDetails ,@NotNull Coupon couponDetails , @NotNull final long productId) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Product product = productService.getProductById(userDetails , productId);
        couponDetails.setBusiness(existingUser);
        couponDetails.setProduct(product);
        final Coupon newCoupon = couponRepository.save(couponDetails);
        final CouponDTO coupon = couponDTOMapper.apply(newCoupon);
        return ResponseHandler.generateResponse(coupon , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateCoupon(@NotNull final UserDetails userDetails,final long couponId, @NotNull Coupon couponDetails) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Coupon existingCoupon = getCouponById(existingUser.getId(),couponId);
        couponDetails.setId(existingCoupon.getId());
        couponDetails.setBusiness(existingCoupon.getBusiness());
        couponDetails.setProduct(existingCoupon.getProduct());
        CouponDTO coupon = couponDTOMapper.apply(couponRepository.save(couponDetails));
        return ResponseHandler.generateResponse(coupon , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCouponById(@NotNull final UserDetails userDetails,final long couponId) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Coupon existingCoupon = getCouponById(existingUser.getId(),couponId);
        couponRepository.deleteCouponById(Optional.of(couponId),existingUser.getId());

        final String successResponse = String.format("The Coupon with ID : %d deleted successfully.", couponId);
        return ResponseHandler.generateResponse(successResponse , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> fetchCouponById(@NotNull final UserDetails userDetails ,final long couponId) {
        UserEntity existingUser = userEntityService.getUserEntityByEmail(userDetails.getUsername());

        Coupon existingCoupon = getCouponById(existingUser.getId(),couponId);
        CouponDTO coupon = couponDTOMapper.apply(existingCoupon);

        return ResponseHandler.generateResponse(coupon , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> fetchCouponsByBusinessId(@NotNull final UserDetails userDetails, final int pageNumber) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        final Pageable pageable = PageRequest.of((int) pageNumber - 1, 10);
        final List<Coupon> couponList = couponRepository.fetchAllCouponOfBusinessById(existingUser.getId() , pageable);
        if(couponList.isEmpty() && pageNumber > 1)
        {
            return fetchCouponsByBusinessId(userDetails ,1);
        }
        final List<CouponDTO> coupons  = couponList.stream().map(couponDTOMapper).toList();
        final int totalPages = (int)Math.ceil(couponRepository.getCountOfCouponsInBusinessById(existingUser.getId()) / 10.0);

        return ResponseHandler.generateResponse(coupons,HttpStatus.OK , coupons.size() , totalPages);

    }

    @Override
    public Coupon getCouponById(@NotNull final UUID userId, long couponId) {
        return couponRepository.fetchCouponByIdOfBusinessId(userId,couponId).orElseThrow(
                () -> new ResourceNotFoundException("This Coupon could not be found in our system.")
        );
    }
}
