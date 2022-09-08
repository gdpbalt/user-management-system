package com.example.usermanagementsystem.service.impl;

import com.example.usermanagementsystem.exception.RefreshTokenException;
import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.repository.RefreshTokenRepository;
import com.example.usermanagementsystem.service.RefreshTokenService;
import com.example.usermanagementsystem.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Value("${security.jwt.token.jwtRefreshExpirationSeconds:86400}")
    private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Override
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(() ->
                new RefreshTokenException("Refresh token not found by token " + token));
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken renewRefreshToken(RefreshToken refreshToken) {
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenDurationMs));
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Refresh token was expired. "
                    + "Please make a new login request. Token=" + refreshToken.getToken());
        }
        return refreshToken;
    }

    @Override
    public Long deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userService.findById(userId));
    }

    @PostConstruct
    @Scheduled(cron = "0 30 7 * * *")
    @Override
    public void deleteExpiredTokens() {
        log.info("Delete old expired tokens");
        List<RefreshToken> refreshTokens =
                refreshTokenRepository.findByExpiryDateLessThan(LocalDateTime.now())
                        .stream()
                        .toList();
        refreshTokenRepository.deleteAll(refreshTokens);
    }
}
