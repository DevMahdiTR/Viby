package com.chefme.chefme.service.pointrecord;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.pointrecord.PointRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface PointRecordService {


    public PointRecord save(PointRecord pointRecord);
    public ResponseEntity<Object> addPointRecord(@NotNull UserDetails userDetails,@NotNull final String clientMail, @NotNull final long pointsToTransmit);
    public PointRecord getPointRecordByIdAndClientId(final UUID pointRecordId , final UUID clientId);
}
