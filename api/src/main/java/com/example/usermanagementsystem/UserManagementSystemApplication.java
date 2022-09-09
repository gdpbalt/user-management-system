package com.example.usermanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UserManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementSystemApplication.class, args);
    }

}
