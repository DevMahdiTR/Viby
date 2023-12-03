package com.chefme.chefme.dto.coupon;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.business.BusinessDTOMapper;
import com.chefme.chefme.dto.product.ProductDTOMapper;
import com.chefme.chefme.model.coupons.Coupon;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CouponDTOMapper  implements Function<Coupon , CouponDTO> {
    @Override
    public CouponDTO apply(Coupon coupon) {
        return new CouponDTO(
                coupon.getId(),
                coupon.getPercentage(),
                coupon.getTotalPoints(),
                coupon.getCouponInSite(),
                new ProductDTOMapper().apply(coupon.getProduct()),
                new BusinessDTOMapper().apply(coupon.getBusiness())
        );
    }
}
