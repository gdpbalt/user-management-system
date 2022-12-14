package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;

public interface RefreshTokenService {

    RefreshToken findByToken(String token);

    RefreshToken findByUser(User user);

    RefreshToken createRefreshToken(User user);

    RefreshToken renewRefreshToken(RefreshToken refreshToken);

    RefreshToken verifyExpiration(RefreshToken refreshToken);

    void deleteByUser(User user);

    void deleteExpiredTokens();
}
