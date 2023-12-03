package com.chefme.chefme.dto.auth;

import com.chefme.chefme.dto.user.UserEntityDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {
    private UserEntityDTO userEntityDTO;
    private String confirmationToken;
    private String refreshToken;

    public RegisterResponseDTO(UserEntityDTO userEntityDTO, String confirmationToken, String refreshToken) {
        this.userEntityDTO = userEntityDTO;
        this.confirmationToken = confirmationToken;
        this.refreshToken = refreshToken;
    }
}
