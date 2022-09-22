package com.example.usermanagementsystem.dto.response;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageDto {
    private int statusCode;
    private Date timestamp;
    private List<String> message;
}
