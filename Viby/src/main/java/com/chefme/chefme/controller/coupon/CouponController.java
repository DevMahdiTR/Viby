package com.chefme.chefme.controller.coupon;

import com.chefme.chefme.model.coupons.Coupon;
import com.chefme.chefme.service.coupon.CouponService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Object> addCoupon(
            @AuthenticationPrincipal @NotNull final UserDetails userDetails ,
            @Valid @NotNull @RequestBody Coupon coupon,
            @PathVariable("productId") @NotNull final long productId
    )
    {
        return couponService.addCoupon(userDetails , coupon, productId);
    }

    @PutMapping("/{couponId}")
    public ResponseEntity<Object> updateCoupon(
            @AuthenticationPrincipal @NotNull final UserDetails userDetails ,
            @PathVariable("couponId") final long couponId,
            @Valid  @NotNull @RequestBody  Coupon coupon)
    {
        return couponService.updateCoupon(userDetails , couponId , coupon);
    }

    @DeleteMapping("/{couponId}")
    public ResponseEntity<Object> deleteCouponById(
            @AuthenticationPrincipal @NotNull final UserDetails userDetails,
            @PathVariable("couponId") final long couponId)
    {
        return couponService.deleteCouponById(userDetails , couponId);
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<Object> fetchCouponById(
            @AuthenticationPrincipal @NotNull final UserDetails userDetails,
            @PathVariable("couponId") final long couponId)
    {
        return couponService.fetchCouponById(userDetails, couponId);
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Object> fetchCouponsByBusinessId(
            @AuthenticationPrincipal @NotNull final UserDetails userDetails,
            @PathVariable("pageNumber") final int pageNumber)
    {
        return couponService.fetchCouponsByBusinessId(userDetails , pageNumber);
    }

}
