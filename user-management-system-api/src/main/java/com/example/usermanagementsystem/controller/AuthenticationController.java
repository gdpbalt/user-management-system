package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.JwtResponseDto;
import com.example.usermanagementsystem.dto.RefreshTokenRequestDto;
import com.example.usermanagementsystem.dto.RefreshTokenResponseDto;
import com.example.usermanagementsystem.dto.UserLoginDto;
import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.exception.RefreshTokenException;
import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.security.AuthenticationService;
import com.example.usermanagementsystem.security.jwt.JwtTokenProvider;
import com.example.usermanagementsystem.service.RefreshTokenService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public JwtResponseDto login(@RequestBody UserLoginDto userLoginDto)
            throws AuthenticationException {
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        String accessToken = jwtTokenProvider.createToken(user.getName(),
                List.of(user.getRole().getName().name()));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return new JwtResponseDto(accessToken, refreshToken.getToken());
    }

    @PostMapping("/refreshtoken")
    public RefreshTokenResponseDto refreshToken(@RequestBody @Valid RefreshTokenRequestDto request)
            throws RefreshTokenException {
        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken());
        refreshToken = refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String accessToken = jwtTokenProvider.createToken(user.getName(),
                List.of(user.getRole().getName().name()));
        refreshToken = refreshTokenService.renewRefreshToken(refreshToken);
        return new RefreshTokenResponseDto(accessToken, refreshToken.getToken());
    }
}
