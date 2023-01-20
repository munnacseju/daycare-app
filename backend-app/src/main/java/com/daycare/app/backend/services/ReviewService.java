package com.daycare.app.backend.services;

import java.util.Optional;

import com.daycare.app.backend.models.Review;
import com.daycare.app.backend.models.User;

public interface ReviewService {
	void save(Review review);
	Optional<Review> findById(Long id);
    Iterable<Review> findByUser(User user);
	Iterable<Review> findByCaregiverId(Long caregiverId);
	void deleteById(Long id);
}
