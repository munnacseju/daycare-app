package com.daycare.app.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.daycare.app.backend.models.Review;
import com.daycare.app.backend.models.User;

public interface ReviewRepository extends CrudRepository <Review, Long> {
    Optional<Review> findById(Long id);
    Iterable<Review> findByUser(User user);
}
