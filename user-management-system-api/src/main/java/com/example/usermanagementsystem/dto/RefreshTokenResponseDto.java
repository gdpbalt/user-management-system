package com.example.usermanagementsystem.dto.mapper;

import lombok.Data;

@Data
public class RefreshTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
