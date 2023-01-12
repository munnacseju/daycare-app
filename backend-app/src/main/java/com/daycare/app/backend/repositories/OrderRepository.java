package com.daycare.app.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.daycare.app.backend.models.Order;
import com.daycare.app.backend.models.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	Optional<Order> findById(Long id);
    Iterable<Order> findByUser(User user);
}
