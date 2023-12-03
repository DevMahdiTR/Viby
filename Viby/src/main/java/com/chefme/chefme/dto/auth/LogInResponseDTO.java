package com.chefme.chefme.dto.auth;

import com.chefme.chefme.dto.user.UserEntityDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogInResponseDTO {
    private UserEntityDTO userEntityDTO;
    private String accessToken;
    private String refreshToken;
}
