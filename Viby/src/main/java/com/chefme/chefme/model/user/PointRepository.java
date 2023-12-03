package com.chefme.chefme.model.user;

import com.chefme.chefme.model.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point ,Integer> {



    @Query(value = "SELECT P FROM Point P WHERE P.business.id = :businessId AND P.client.id = :clientId")
    Optional<Point> fetchPointByBusinessAndClient(@Param("businessId") final UUID businessId , @Param("clientId") final UUID clientId);

    @Query("SELECT EXISTS(SELECT P FROM Point  P WHERE P.business.id = :busninessId and P.client.id = :clientId) AS RESULT")
    boolean isPointExist(@Param("busninessId") final UUID businessId , @Param("clientId") final UUID clientId);
}
