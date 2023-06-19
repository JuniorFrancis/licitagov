package com.exame.licitagov.repositorys;

import com.exame.licitagov.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);
}
