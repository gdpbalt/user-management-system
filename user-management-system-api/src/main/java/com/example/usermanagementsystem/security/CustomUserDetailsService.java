package com.example.usermanagementsystem.security;

import com.example.usermanagementsystem.exception.UserOrRoleNotFoundException;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findByName(username);
        } catch (UserOrRoleNotFoundException e) {
            throw new UsernameNotFoundException("User not found with name " + username);
        }

        UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles(user.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
