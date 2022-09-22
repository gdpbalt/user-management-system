package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.response.RoleResponseDto;
import com.example.usermanagementsystem.dto.mapper.ResponseDtoMapper;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.service.RoleService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final ResponseDtoMapper<RoleResponseDto, Role> roleResponseDtoMapper;

    @GetMapping
    @ApiOperation(value = "Get list of all roles")
    public List<RoleResponseDto> findAll() {
        log.info("Get list of all roles");
        return roleService.findAll().stream()
                .map(roleResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
