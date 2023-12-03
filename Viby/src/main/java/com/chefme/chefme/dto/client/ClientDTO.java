package com.chefme.chefme.dto.client;

import com.chefme.chefme.dto.point.PointDTO;
import com.chefme.chefme.model.role.Role;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ClientDTO (

        UUID id,
        String username ,
        String email,
        String address,
        Date creatingDate,
        Boolean isEnabled ,
        Role role,
        List<PointDTO> points


) {
}
