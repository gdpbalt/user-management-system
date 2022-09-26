package com.example.usermanagementsystem.security;

import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String authenticationMessage = "Incorrect username or password. User=" + login;
        User user;
        try {
            user = userService.findByName(login);
        } catch (EntityNotFoundException e) {
            throw new AuthenticationException(authenticationMessage);
        }
        if (passwordEncoder.matches(password, user.getPassword())
                && user.getStatus() == Status.ACTIVE) {
            return user;
        }
        throw new AuthenticationException(authenticationMessage);
    }
}
