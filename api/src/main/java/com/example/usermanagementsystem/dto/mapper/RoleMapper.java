package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.response.RoleResponseDto;
import com.example.usermanagementsystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements ResponseDtoMapper<RoleResponseDto, Role> {

    @Override
    public RoleResponseDto toDto(Role role) {
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName().name());
        return dto;
    }
}
