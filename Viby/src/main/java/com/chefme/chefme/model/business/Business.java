package com.chefme.chefme.model.business;


import com.chefme.chefme.model.coupons.Coupon;
import com.chefme.chefme.model.point.Point;
import com.chefme.chefme.model.product.Product;
import com.chefme.chefme.model.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "businesses")

public class Business  extends UserEntity {


    @OneToMany
    private List<Coupon> coupons;


    @OneToMany
    private List<Product> products;

}
