package com.chefme.chefme.model.coupons;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "coupons")

public class Coupon {

    @SequenceGenerator(
            name =  "coupon_sequence",
            sequenceName = "coupon_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "coupon_sequence")
    @Id
    @Column(name = "id", unique = true , nullable = false)
    private long id;

    @Column(name = "percentage" , nullable = false)
    private float percentage;

    @Column(name = "coupon_in_site" , nullable = false)
    private String couponInSite;

    @Column(name = "total_points" , nullable = false)
    private long totalPoints;

    @ManyToOne
    private Business business;

    @OneToOne
    private Product product;

}
