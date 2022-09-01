package com.example.usermanagementsystem.config;

import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "user";
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void dataInitialisation() {
        try {
            userService.findByName(ADMIN_LOGIN);
        } catch (EntityNotFoundException e) {
            User adminUser = new User();
            adminUser.setName(ADMIN_LOGIN);
            adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            adminUser.setFirstName("User for admin purpose");
            adminUser.setLastName("");
            adminUser.setRole(roleService.findByName(RoleName.ADMIN));
            adminUser.setStatus(Status.ACTIVE);
            userService.save(adminUser);
        }
        try {
            userService.findByName(USER_LOGIN);
        } catch (EntityNotFoundException e) {
            User userUser = new User();
            userUser.setName(USER_LOGIN);
            userUser.setPassword(passwordEncoder.encode(USER_PASSWORD));
            userUser.setFirstName("User for admin purpose");
            userUser.setLastName("");
            userUser.setRole(roleService.findByName(RoleName.USER));
            userUser.setStatus(Status.ACTIVE);
            userService.save(userUser);
        }
    }
}
