package com.chefme.chefme.repository;

import com.chefme.chefme.model.pointrecord.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface PointRecordRepository  extends JpaRepository<PointRecord , Integer> {


    @Query(value = "SELECT PR FROM PointRecord PR WHERE PR.id = :pointRecordId AND PR.client.id = :clientId AND PR.isCollected = false")
    Optional<PointRecord> fetchPointRecordByIdAndClientId(@Param("pointRecordId") Optional<UUID>  pointRecordId, @Param("clientId") final UUID clientId);


}
