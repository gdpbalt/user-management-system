package com.example.usermanagementsystem.service;

import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.model.Status;
import com.example.usermanagementsystem.model.User;
import com.example.usermanagementsystem.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found by id " + id));
    }

    @Override
    public User findByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new EntityNotFoundException("User not found by name " + name);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User lock(Long id) {
        return changeUserStatus(id, Status.INACTIVE);
    }

    @Override
    public User unlock(Long id) {
        return changeUserStatus(id, Status.ACTIVE);
    }

    private User changeUserStatus(Long id, Status newStatus) {
        User user = findById(id);
        user.setStatus(newStatus);
        userRepository.save(user);
        return user;
    }
}
