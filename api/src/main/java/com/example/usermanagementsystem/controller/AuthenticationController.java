package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.response.AuthenticationResponseDto;
import com.example.usermanagementsystem.dto.request.RefreshTokenRequestDto;
import com.example.usermanagementsystem.dto.request.UserRequestLoginDto;
import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.exception.RefreshTokenException;
import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.security.AuthenticationService;
import com.example.usermanagementsystem.security.jwt.JwtTokenProvider;
import com.example.usermanagementsystem.service.RefreshTokenService;
import com.example.usermanagementsystem.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody UserRequestLoginDto userLoginDto)
            throws AuthenticationException {
        log.info("User {} is coming fot authentication", userLoginDto.getLogin());
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        String accessToken = jwtTokenProvider.createToken(user.getName(),
                List.of(user.getRole().getName().name()));
        RefreshToken refreshToken;
        try {
            refreshToken = refreshTokenService.findByUser(user);
            refreshToken = refreshTokenService.renewRefreshToken(refreshToken);
        } catch (RefreshTokenException e) {
            refreshToken = refreshTokenService.createRefreshToken(user);
        }
        return new AuthenticationResponseDto(accessToken, refreshToken.getToken(), user.getName(),
                user.getRole().getName().name());
    }

    @PostMapping("/refreshtoken")
    public AuthenticationResponseDto refreshToken(
            @RequestBody @Valid RefreshTokenRequestDto request)
            throws RefreshTokenException {
        log.info("User is coming for refreshing the token {}", request.getRefreshToken());
        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken());
        refreshToken = refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        String accessToken = jwtTokenProvider.createToken(user.getName(),
                List.of(user.getRole().getName().name()));
        refreshToken = refreshTokenService.renewRefreshToken(refreshToken);
        return new AuthenticationResponseDto(accessToken, refreshToken.getToken(), user.getName(),
                user.getRole().getName().name());
    }

    @GetMapping("/signout")
    public void logout(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        String username = details.getUsername();
        log.info("User is coming for logout username is {}", username);
        User user = userService.findByName(username);
        refreshTokenService.deleteByUser(user);
    }
}
