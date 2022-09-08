package com.example.usermanagementsystem.dto;

import lombok.Data;

@Data
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public JwtResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
