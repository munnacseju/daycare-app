package com.daycare.app.backend.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daycare.app.backend.models.Baby;
import com.daycare.app.backend.models.User;
import com.daycare.app.backend.repositories.BabyRepository;
import com.daycare.app.backend.repositories.UserRepository;
import com.daycare.app.backend.services.BabyService;

@Service
public class BabyServiceImpl implements BabyService {

	@Autowired
	private BabyRepository babyRepository;
	
	@Override
	public void save(Baby baby) {
		babyRepository.save(baby); 
	}

	@Override
	public Optional<Baby> findById(Long id) {
		return babyRepository.findById(id);
	}

//	@Override
//	public Optional<Baby> findByIdANDIsSicked(Long id, boolean isSicked) {
//		return babyRepository.findByIdANDIsSicked(id, isSicked);
//	}

	@Override
	public Iterable<Baby> findByUser(User user) {
		return babyRepository.findByUser(user);
	}

}
