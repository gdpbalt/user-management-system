package com.example.usermanagementsystem.dto;

import com.example.usermanagementsystem.model.RoleName;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private RoleName role;
    private String status;
    private LocalDateTime createdAt;
}
