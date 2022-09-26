package com.example.usermanagementsystem.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestLoginDto {
    @NotBlank(message = "can't be null or blank")
    private String login;
    @NotBlank(message = "can't be null or blank")
    private String password;
}
