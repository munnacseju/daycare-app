package com.daycare.app.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daycare.app.backend.models.Review;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.repositories.ReviewRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class CaregiverReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public void save(Review review) {
        reviewRepository.save(review);
        
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Iterable<Review> findByUser(User user) {
        return reviewRepository.findByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Iterable<Review> findByCaregiverId(Long caregiverId) {
        return reviewRepository.findByCaregiverId(caregiverId);
    }
    
}
