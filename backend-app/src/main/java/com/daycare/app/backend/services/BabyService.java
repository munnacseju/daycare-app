package com.daycare.app.backend.services;

import java.util.Optional;

import com.daycare.app.backend.models.Baby;
import com.daycare.app.backend.models.User;

public interface BabyService {
	void save(Baby baby);
	Optional<Baby> findById(Long id);
//	Optional<Baby> findByIdANDIsSicked(Long id, boolean isSicked);
    Iterable<Baby> findByUser(User user);
}
