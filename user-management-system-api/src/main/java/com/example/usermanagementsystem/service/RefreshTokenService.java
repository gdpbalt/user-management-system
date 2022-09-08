package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken createRefreshToken(User user);

    RefreshToken renewRefreshToken(RefreshToken refreshToken);

    RefreshToken verifyExpiration(RefreshToken refreshToken);

    Long deleteByUserId(Long userId);

    void deleteExpiredTokens();
}
