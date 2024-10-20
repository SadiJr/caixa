package com.omegaeducacional.caixa.repository;

import com.omegaeducacional.caixa.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
