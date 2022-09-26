package com.example.usermanagementsystem.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_VALIDATION_REGEX = "^[a-zA-Z0-9]+$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null
                || password.length() < 3
                || password.length() > 16
                || !password.matches(PASSWORD_VALIDATION_REGEX)) {
            return false;
        }
        boolean hasOneCharacter = false;
        boolean hasOneNumber = false;
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasOneCharacter = true;
            }
            if (Character.isDigit(ch)) {
                hasOneNumber = true;
            }
        }
        return hasOneCharacter && hasOneNumber;
    }
}
