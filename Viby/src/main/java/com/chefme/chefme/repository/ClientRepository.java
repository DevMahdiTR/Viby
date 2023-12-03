package com.chefme.chefme.repository;

import com.chefme.chefme.model.client.Client;
import com.chefme.chefme.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface ClientRepository  extends JpaRepository<Client, Integer> {


    @Query(value = "SELECT C FROM Client C WHERE C.id = :clientId")
    Optional<Client> fetchClientById(@Param("clientId") final UUID clientId);
}
