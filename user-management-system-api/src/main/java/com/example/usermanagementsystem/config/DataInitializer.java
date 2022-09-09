package com.example.usermanagementsystem.config;

import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "test123";
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "test123";
    private final RoleService roleService;
    private final UserService userService;

    @PostConstruct
    public void dataInitialisation() {
        try {
            userService.findByName(ADMIN_USERNAME);
        } catch (EntityNotFoundException e) {
            User adminUser = new User();
            adminUser.setName(ADMIN_USERNAME);
            adminUser.setPassword(ADMIN_PASSWORD);
            adminUser.setFirstName("adminFirstName");
            adminUser.setLastName("adminLastName");
            adminUser.setRole(roleService.findByName(RoleName.ADMIN));
            adminUser.setStatus(Status.ACTIVE);
            userService.save(adminUser);
        }
        try {
            userService.findByName(USER_USERNAME);
        } catch (EntityNotFoundException e) {
            User userUser = new User();
            userUser.setName(USER_USERNAME);
            userUser.setPassword(USER_PASSWORD);
            userUser.setFirstName("userFirstName");
            userUser.setLastName("userLastName");
            userUser.setRole(roleService.findByName(RoleName.USER));
            userUser.setStatus(Status.ACTIVE);
            userService.save(userUser);
        }
    }
}
