package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.StatusResponseDto;
import com.example.usermanagementsystem.dto.mapper.ResponseDtoMapper;
import com.example.usermanagementsystem.model.Status;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
@CrossOrigin
public class StatusController {
    private final ResponseDtoMapper<StatusResponseDto, String> statusResponseDtoMapper;

    @GetMapping
    @ApiOperation(value = "Get list statuses for user")
    public List<StatusResponseDto> getAll() {
        return Arrays.stream(Status.values())
                .map(s -> statusResponseDtoMapper.toDto(s.name()))
                .collect(Collectors.toList());
    }
}
