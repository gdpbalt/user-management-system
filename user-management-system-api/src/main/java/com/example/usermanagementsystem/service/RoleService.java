package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.RoleName;
import java.util.List;

public interface RoleService {
    Role findById(Long id);

    Role findByName(RoleName name);

    List<Role> findAll();
}
