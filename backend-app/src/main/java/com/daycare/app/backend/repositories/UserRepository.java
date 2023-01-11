package com.daycare.app.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.daycare.app.backend.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
