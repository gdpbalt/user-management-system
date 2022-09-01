package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.UserRequestDto;
import com.example.usermanagementsystem.dto.UserResponseDto;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.RoleService;
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
        user.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));

        RoleName roleName = RoleName.valueOf(dto.getRole().toUpperCase());
        user.setRole(roleService.findByName(roleName));
        return user;
    }

    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole().getName());
        dto.setStatus(user.getStatus().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
