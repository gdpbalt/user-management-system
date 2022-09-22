package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.request.UserRequestDto;
import com.example.usermanagementsystem.dto.response.UserResponseDto;
import com.example.usermanagementsystem.dto.mapper.RequestDtoMapper;
import com.example.usermanagementsystem.dto.mapper.ResponseDtoMapper;
import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RequestDtoMapper<UserRequestDto, User> userRequestDtoMapper;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    @GetMapping
    @ApiOperation(value = "Get list of all users")
    public List<UserResponseDto> findAll() {
        log.info("Get list of all users");
        return userService.findAll().stream()
                .map(userResponseDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get information about the user by id")
    public UserResponseDto findUserById(@PathVariable Long id) {
        return userResponseDtoMapper.toDto(userService.findById(id));
    }

    @GetMapping("/{id}/lock")
    @ApiOperation(value = "Lock user")
    public UserResponseDto lockUser(@PathVariable Long id) {
        return userResponseDtoMapper.toDto(userService.lock(id));
    }

    @GetMapping("/{id}/unlock")
    @ApiOperation(value = "Unlock user")
    public UserResponseDto unlockUser(@PathVariable Long id) {
        return userResponseDtoMapper.toDto(userService.unlock(id));
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "Get information about the user by name")
    public UserResponseDto findUserByName(@RequestParam String name) {
        return userResponseDtoMapper.toDto(userService.findByName(name));
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    public UserResponseDto createUser(@RequestBody @Valid UserRequestDto dto) {
        User user = userRequestDtoMapper.toModel(dto);
        return userResponseDtoMapper.toDto(userService.save(user));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update information about the user")
    public UserResponseDto updateUser(@RequestBody @Valid UserRequestDto dto,
                                      @PathVariable Long id) {
        try {
            userService.findById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not found for update by id " + id);
        }
        User user = userRequestDtoMapper.toModel(dto);
        user.setId(id);
        return userResponseDtoMapper.toDto(userService.save(user));
    }
}
