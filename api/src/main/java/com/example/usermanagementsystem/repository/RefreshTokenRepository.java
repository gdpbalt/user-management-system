package com.example.usermanagementsystem.repository;

import com.example.usermanagementsystem.model.RefreshToken;
import com.example.usermanagementsystem.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser(User user);

    List<RefreshToken> findByExpiryDateLessThan(LocalDateTime date);

    Long deleteByUser(User user);

    Long deleteByToken(RefreshToken token);
}
