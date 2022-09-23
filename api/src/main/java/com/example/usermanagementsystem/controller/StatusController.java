package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.mapper.ResponseDtoMapper;
import com.example.usermanagementsystem.dto.response.StatusResponseDto;
import com.example.usermanagementsystem.model.Status;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {
    private final ResponseDtoMapper<StatusResponseDto, String> statusResponseDtoMapper;

    @GetMapping
    @ApiOperation(value = "Get list statuses for user")
    public List<StatusResponseDto> getAll() {
        log.info("Get list statuses for user");
        return Arrays.stream(Status.values())
                .map(s -> statusResponseDtoMapper.toDto(s.name()))
                .collect(Collectors.toList());
    }
}
