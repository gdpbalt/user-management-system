package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.UserLoginDto;
import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.security.AuthenticationService;
import com.example.usermanagementsystem.security.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto)
            throws AuthenticationException {
        User user = authenticationService.login(userLoginDto.getLogin(),
                userLoginDto.getPassword());
        String token = jwtTokenProvider.createToken(user.getName(),
                List.of(user.getRole().getName().name()));
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
