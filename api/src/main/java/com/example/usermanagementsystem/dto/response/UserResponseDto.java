package com.example.usermanagementsystem.dto.response;

import com.example.usermanagementsystem.model.RoleName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private RoleName role;
    private String status;
    private LocalDateTime createdAt;
}
