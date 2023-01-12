package com.daycare.app.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.daycare.app.backend.models.Baby;
import com.daycare.app.backend.models.User;

public interface BabyRepository extends CrudRepository<Baby, Long> {
	Optional<Baby> findById(Long id);
    Iterable<Baby> findByUser(User user);
}
