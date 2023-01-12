package com.daycare.app.backend.services;

import java.util.Optional;

import com.daycare.app.backend.models.Order;
import com.daycare.app.backend.models.User;

public interface OrderService {
	void save(Order service);
	Optional<Order> findById(Long id);
    Iterable<Order> findByUser(User user);
	void deleteById(Long id);
}