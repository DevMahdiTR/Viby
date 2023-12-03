package com.chefme.chefme.dto.coupon;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.product.ProductDTO;
import com.chefme.chefme.model.business.Business;

public record CouponDTO (

        long id,
        float percentage,
        long totalPrice,
        String couponInSite,
        ProductDTO productDTO,
        BusinessDTO businessDTO

) {
}
