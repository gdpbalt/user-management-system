package com.example.usermanagementsystem.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestLoginDto {
    @NotBlank(message = "can't be null or blank")
    private String login;
    @NotBlank(message = "can't be null or blank")
    private String password;
}
