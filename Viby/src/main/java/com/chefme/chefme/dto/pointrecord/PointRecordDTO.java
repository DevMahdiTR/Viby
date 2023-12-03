package com.chefme.chefme.dto.pointrecord;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.client.ClientDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public record PointRecordDTO (
        UUID id,
        LocalDate createdAt,
        long pointsToTransmit,
        boolean isCollected,
        BusinessDTO businessDTO,
        ClientDTO clientDTO
){
}
