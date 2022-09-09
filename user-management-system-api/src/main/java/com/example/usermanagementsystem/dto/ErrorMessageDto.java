package com.example.usermanagementsystem.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
