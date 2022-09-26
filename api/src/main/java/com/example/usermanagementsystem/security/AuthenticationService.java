package com.example.usermanagementsystem.security;

import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.model.User;

public interface AuthenticationService {

    User login(String login, String password) throws AuthenticationException;
}
