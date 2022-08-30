package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.exception.UserOrRoleNotFoundException;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.repository.RoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new UserOrRoleNotFoundException("Role not found by id " + id));
    }

    @Override
    public Role findByName(RoleName name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new UserOrRoleNotFoundException("Role not found by name " + name);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
