package com.example.usermanagementsystem.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequestDto {
    @NotBlank
    private String refreshToken;
}
