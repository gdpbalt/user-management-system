package com.example.usermanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserOrRoleNotFoundException extends RuntimeException {
    public UserOrRoleNotFoundException(String message) {
        super(message);
    }
}
