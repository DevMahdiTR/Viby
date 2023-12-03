package com.chefme.chefme.dto.business;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.coupon.CouponDTOMapper;
import com.chefme.chefme.dto.product.ProductDTOMapper;
import com.chefme.chefme.model.business.Business;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BusinessDTOMapper implements Function<Business, BusinessDTO> {
    @Override
    public BusinessDTO apply(Business business) {
        return new BusinessDTO(
                business.getId(),
                business.getUsername(),
                business.getEmail(),
                business.getAddress(),
                business.getCreatingDate(),
                business.isEnabled(),
                business.getRole(),
                business.getCoupons().stream().map(coupon ->  new CouponDTOMapper().apply(coupon)).toList(),
                business.getProducts().stream().map(product -> new ProductDTOMapper().apply(product)).toList()
        );
    }
}
