package com.example.usermanagementsystem.dto;

import lombok.Data;

@Data
public class AuthenticationResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private String user;
    private String role;

    public AuthenticationResponseDto(String accessToken, String refreshToken, String user,
                                     String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
        this.role = role;
    }
}
