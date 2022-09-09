package com.example.usermanagementsystem.lib;

import com.example.usermanagementsystem.model.RoleName;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    @Override
    public boolean isValid(String roleName, ConstraintValidatorContext context) {
        if (roleName == null) {
            return false;
        }
        try {
            RoleName.valueOf(roleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
