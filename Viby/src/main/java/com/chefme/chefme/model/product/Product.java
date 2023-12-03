package com.chefme.chefme.model.product;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.file.FileData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {

    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy =  GenerationType.SEQUENCE ,generator = "product_sequence")
    @Id
    @Column(name = "id", unique = true , nullable = false)
    private long id;


    @Column(name  = "name", nullable = false)
    private String name;

    @Column(name = "price" , nullable = false)
    private double price;

    @Column(name = "points_to_redeem" , nullable = false)
    private  long pointToRedeem;

    @OneToOne
    private FileData fileData;

    @OneToOne
    private Business business;


}
