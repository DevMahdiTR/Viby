package com.chefme.chefme.repository;

import com.chefme.chefme.model.client.Client;
import com.chefme.chefme.model.coupons.Coupon;
import com.chefme.chefme.model.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository  extends JpaRepository<Coupon, Integer> {

    @Query(value = "SELECT C FROM Coupon C WHERE C.id = :couponId")
    Optional<Coupon> fetchCouponById(@Param("couponId") final long couponId);



    @Query(value = "SELECT C FROM  Coupon  C WHERE  C.business.id = :businessId")
    List<Coupon> fetchAllCouponOfBusinessById(@Param("businessId") final UUID businessId , Pageable pageable);

    @Query(value = "SELECT COUNT(C) FROM Coupon C WHERE  C.business.id = :businessId")
    int getCountOfCouponsInBusinessById(@Param("businessId") final UUID businessId);

    @Query(value = "SELECT C FROM Coupon  C WHERE  C.business.id = :businessId AND C.id = :couponId")
    Optional<Coupon> fetchCouponByIdOfBusinessId(@Param("businessId") final UUID businessId , @Param("couponId") final long couponId );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Coupon  C  WHERE C.id = :couponId AND C.business.id = :businessId")
    void deleteCouponById(@Param("couponId")  Optional<Long> couponId , @Param("businessId") final UUID businessId );



}
