package com.example.usermanagementsystem.lib;

import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.service.RoleService;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleSetValidator implements ConstraintValidator<ValidRoleSet, Set<Long>> {
    private final RoleService roleService;

    @Override
    public boolean isValid(Set<Long> roleIds, ConstraintValidatorContext context) {
        if (roleIds == null || roleIds.isEmpty()) {
            return false;
        }
        for (Long id : roleIds) {
            try {
                roleService.findById(id);
            } catch (EntityNotFoundException e) {
                return false;
            }
        }
        return true;
    }
}
