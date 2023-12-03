package com.chefme.chefme.repository;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface BusinessRepository  extends JpaRepository<Business , Integer> {


    @Query(value = "SELECT B FROM Business B ")
    List<Business> fetchAllBusinesses(Pageable pageable);

    @Query(value = "SELECT B FROM Business B WHERE B.id = :businessId")
    Optional<Business> fetchBusinessById(@Param("businessId") final UUID businessId);


}
