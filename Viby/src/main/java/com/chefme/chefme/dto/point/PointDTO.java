package com.chefme.chefme.dto.point;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.client.ClientDTO;

public record PointDTO (

        long id,
        long totalPoints,
        BusinessDTO businessDTO,
        ClientDTO clientDTO
) {
}
