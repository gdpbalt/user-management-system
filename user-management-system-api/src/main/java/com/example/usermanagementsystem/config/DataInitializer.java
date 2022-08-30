package com.example.usermanagementsystem.config;

import com.example.usermanagementsystem.exception.UserOrRoleNotFoundException;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.RoleService;
import com.example.usermanagementsystem.service.UserService;
import java.util.Set;
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
        Role adminRole = roleService.findByName(RoleName.ADMIN);
        Role userRole = roleService.findByName(RoleName.USER);
        try {
            userService.findByName(ADMIN_LOGIN);
        } catch (UserOrRoleNotFoundException e) {
            User adminUser = new User();
            adminUser.setName(ADMIN_LOGIN);
            adminUser.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            adminUser.setFirstName("User for admin purpose");
            adminUser.setLastName("");
            adminUser.setRoles(Set.of(adminRole, userRole));
            adminUser.setStatus(Status.ACTIVE);
            userService.save(adminUser);
        }
        try {
            userService.findByName(USER_LOGIN);
        } catch (UserOrRoleNotFoundException e) {
            User userUser = new User();
            userUser.setName(USER_LOGIN);
            userUser.setPassword(passwordEncoder.encode(USER_PASSWORD));
            userUser.setFirstName("User for admin purpose");
            userUser.setLastName("");
            userUser.setRoles(Set.of(userRole));
            userUser.setStatus(Status.ACTIVE);
            userService.save(userUser);
        }
    }
}
