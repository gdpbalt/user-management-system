package com.example.usermanagementsystem.dto;

import lombok.Data;

@Data
public class RefreshTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public RefreshTokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
