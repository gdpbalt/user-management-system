package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.Role;
import com.example.usermanagementsystem.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Role findByName(RoleName name);
}
