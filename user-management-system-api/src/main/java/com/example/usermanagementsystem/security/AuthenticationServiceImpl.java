package com.example.usermanagementsystem.security;

import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.exception.UserOrRoleNotFoundException;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    public static final String INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User user;
        try {
            user = userService.findByName(login);
        } catch (UserOrRoleNotFoundException e) {
            throw new AuthenticationException(INCORRECT_USERNAME_OR_PASSWORD);
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new AuthenticationException(INCORRECT_USERNAME_OR_PASSWORD);
    }
}
