package com.chefme.chefme.dto.user;


import com.chefme.chefme.model.role.Role;

import java.util.Date;
import java.util.UUID;

public record UserEntityDTO (
        UUID id,
        String username,
        String email,
        String address,
        Date creationDate,
        boolean isEnabled,
        Role role
){
}
