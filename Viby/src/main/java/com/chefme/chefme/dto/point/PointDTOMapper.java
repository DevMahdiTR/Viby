package com.chefme.chefme.dto.point;

import com.chefme.chefme.dto.business.BusinessDTOMapper;
import com.chefme.chefme.dto.client.ClientDTOMapper;
import com.chefme.chefme.model.point.Point;
import org.springframework.stereotype.Service;


import java.util.function.Function;

@Service
public class PointDTOMapper implements Function<Point, PointDTO> {
    @Override
    public PointDTO apply(Point point) {
        return new PointDTO(
                point.getId(),
                point.getTotalPoints(),
                new BusinessDTOMapper().apply(point.getBusiness()),
                new ClientDTOMapper().apply(point.getClient())
        );
    }
}
