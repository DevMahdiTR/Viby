package com.chefme.chefme.dto.pointrecord;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.business.BusinessDTOMapper;
import com.chefme.chefme.dto.client.ClientDTOMapper;
import com.chefme.chefme.model.pointrecord.PointRecord;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PointRecordDTOMapper implements Function<PointRecord , PointRecordDTO> {
    @Override
    public PointRecordDTO apply(PointRecord pointRecord) {
        return new PointRecordDTO(
                pointRecord.getId() ,
                pointRecord.getCreatedAt(),
                pointRecord.getPointsToTransmit(),
                pointRecord.isCollected(),
                new BusinessDTOMapper().apply(pointRecord.getBusiness()),
                new ClientDTOMapper().apply(pointRecord.getClient())
        );
    }
}
