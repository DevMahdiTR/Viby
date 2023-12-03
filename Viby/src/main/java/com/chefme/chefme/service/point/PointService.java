package com.chefme.chefme.service.point;

import com.chefme.chefme.model.point.Point;
import com.chefme.chefme.model.user.UserEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface PointService {


    public ResponseEntity<Object> collectPoints(@NotNull UserDetails userDetails , @NotNull final UUID pointRecordId);


    public boolean isBusinessPointExistInUser(@NotNull final UUID businessId, @NotNull final UUID clientId);


    public Point getPointByBusinessAndClientId(@NotNull final UUID businessId , @NotNull final UUID clientId);


}
