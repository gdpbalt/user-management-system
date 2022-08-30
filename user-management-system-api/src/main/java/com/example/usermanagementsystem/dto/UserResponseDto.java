package com.example.usermanagementsystem.dto;

import java.time.ZonedDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private Set<Long> roleIds;
    private String status;
    private ZonedDateTime createdAt;
}
