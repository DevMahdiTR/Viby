package com.chefme.chefme.service.coupon;


import com.chefme.chefme.model.coupons.Coupon;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;


public interface CouponService {

    public ResponseEntity<Object> addCoupon(@NotNull final UserDetails userDetails, @Valid @NotNull final Coupon coupon , @NotNull final  long productId);
    public ResponseEntity<Object> updateCoupon(@NotNull final UserDetails userDetails ,final long couponId, @NotNull Coupon coupon);
    public ResponseEntity<Object> deleteCouponById(@NotNull final UserDetails userDetails,final long couponId);
    public ResponseEntity<Object> fetchCouponById(@NotNull final UserDetails userDetails,final long couponId);
    public ResponseEntity<Object> fetchCouponsByBusinessId(@NotNull final UserDetails userDetails, final int pageNumber) ;
    public Coupon getCouponById(@NotNull final UUID userId, long couponId) ;
}
