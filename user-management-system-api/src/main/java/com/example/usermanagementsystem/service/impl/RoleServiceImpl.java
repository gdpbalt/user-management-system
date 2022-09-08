package com.example.usermanagementsystem.service.impl;

import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.RoleName;
import com.example.usermanagementsystem.repository.RoleRepository;
import com.example.usermanagementsystem.service.RoleService;
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
                new EntityNotFoundException("Role not found by id " + id));
    }

    @Override
    public Role findByName(RoleName name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            throw new EntityNotFoundException("Role not found by name " + name);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
