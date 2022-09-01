package com.example.usermanagementsystem.dto;

import com.example.usermanagementsystem.lib.ValidPassword;
import com.example.usermanagementsystem.lib.ValidRoleSet;
import com.example.usermanagementsystem.lib.ValidStatus;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "mustn't be blank")
    @Size(min = 3, max = 16, message = "size should be between 3 and 16")
    @Pattern(regexp = "[a-zA-Z]+", message = "must be only latin characters")
    private String name;
    @ValidPassword(message = "isn't valid, should be 3-16 characters and numbers and "
            + "has minimum 1 character and 1 number")
    private String password;
    @NotBlank(message = "mustn't be blank")
    @Size(min = 1, max = 16, message = "size should be between 1 and 16")
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain special characters")
    @NotBlank(message = "mustn't be blank")
    private String firstName;
    @Size(min = 1, max = 16, message = "size should be between 1 and 16")
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain special characters")
    private String lastName;
    @ValidRoleSet(message = "should have set id of existed roles")
    private Set<Long> roleIds;
    @ValidStatus(message = "should contain ACTIVE or INACTIVE")
    private String status;
}
