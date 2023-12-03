package com.chefme.chefme.dto.user;

import com.chefme.chefme.model.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserEntityDTOMapper  implements Function<UserEntity, UserEntityDTO> {
    @Override
    public UserEntityDTO apply(UserEntity userEntity) {
        return new UserEntityDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getAddress(),
                userEntity.getCreatingDate(),
                userEntity.isEnabled(),
                userEntity.getRole()
        );
    }
}
