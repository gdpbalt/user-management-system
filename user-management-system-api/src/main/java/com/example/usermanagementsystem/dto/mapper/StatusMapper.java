package com.example.usermanagementsystem.dto.mapper;

import com.example.usermanagementsystem.dto.StatusResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper implements ResponseDtoMapper<StatusResponseDto, String> {

    @Override
    public StatusResponseDto toDto(String status) {
        StatusResponseDto dto = new StatusResponseDto();
        dto.setName(status);
        return dto;
    }
}
