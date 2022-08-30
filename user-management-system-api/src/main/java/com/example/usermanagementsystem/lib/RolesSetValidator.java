package com.example.usermanagementsystem.lib;

import com.example.usermanagementsystem.exception.UserOrRoleNotFoundException;
import com.example.usermanagementsystem.service.RoleService;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolesSetValidator implements ConstraintValidator<ValidRolesSet, Set<Long>> {
    private final RoleService roleService;

    @Override
    public boolean isValid(Set<Long> roleIds, ConstraintValidatorContext context) {
        if (roleIds == null || roleIds.isEmpty()) {
            return false;
        }
        for (Long id : roleIds) {
            try {
                roleService.findById(id);
            } catch (UserOrRoleNotFoundException e) {
                return false;
            }
        }
        return true;
    }
}
