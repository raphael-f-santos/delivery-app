package com.delivery.app.eatery_ms.repositories;

import com.delivery.app.eatery_ms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
