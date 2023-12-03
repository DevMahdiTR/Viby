package com.chefme.chefme.repository;


import com.chefme.chefme.model.product.Product;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = false)
public interface ProductRepository  extends JpaRepository<Product , Integer> {


    @Query(value ="SELECT P FROM Product P WHERE P.id = :productId")
    Optional<Product> fetchProductById(@Param("productId") final long productId);

    @Query(value = "SELECT P FROM Product P")
    List<Product> fetchAllProductByPage(Pageable pageable);

    @Query(value = "SELECT P FROM Product  P WHERE P.business.id = :businessId AND P.id = :productId")
    Optional<Product> fetchProductByIdOfBusiness(@Param("productId") final long productId, @Param("businessId") final UUID businessId);

    @Query(value = "SELECT COUNT(P) FROM Product P WHERE  P.business.id = :businessId")
    int getCountOfProductInBusinessById(@Param("businessId") final UUID businessId);

    @Query(value = "SELECT P FROM Product  P WHERE P.business.id = :businessId")
    List<Product> fetchAllProductOfBusinessById(@Param("businessId") final UUID businessId , Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Product  P WHERE  P.id = :productId AND P.business.id = :businessId")
    void deleteProductByIdOfBusinessById(@Param("productId") final long productId , @Param("businessId") final  UUID businessId);

}
