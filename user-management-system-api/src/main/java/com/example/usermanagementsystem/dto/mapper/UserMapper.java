package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.UserRequestDto;
import com.example.usermanagementsystem.dto.UserResponseDto;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.RoleService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements RequestDtoMapper<UserRequestDto, User>,
        ResponseDtoMapper<UserResponseDto, User> {
    private final RoleService roleService;

    @Override
    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setStatus(Status.valueOf(dto.getStatus()));

        Set<Role> roles = dto.getRoleIds().stream()
                .map(roleService::findById)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setStatus(user.getStatus().name());
        dto.setCreatedAt(user.getCreatedAt());

        Set<Long> roleIds = user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toSet());
        dto.setRoleIds(roleIds);
        return dto;
    }
}
