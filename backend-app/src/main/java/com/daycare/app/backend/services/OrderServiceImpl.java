package com.daycare.app.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daycare.app.backend.models.Order;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void save(Order order) {
		orderRepository.save(order);
		
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Iterable<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
		
	}
    
}
