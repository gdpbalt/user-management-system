package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.User;
import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByName(String name);

    List<User> findAll();

    User save(User user);
}
