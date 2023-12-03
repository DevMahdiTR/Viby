package com.chefme.chefme.dto.business;

import com.chefme.chefme.dto.coupon.CouponDTO;
import com.chefme.chefme.dto.product.ProductDTO;
import com.chefme.chefme.model.product.Product;
import com.chefme.chefme.model.role.Role;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record BusinessDTO  (
        UUID id,
        String username,
        String email,
        String address,
        Date creatingDate,
        Boolean  isEnabled,
        Role role,
        List<CouponDTO> coupons,
        List<ProductDTO> products


){
}
