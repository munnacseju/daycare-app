package com.daycare.app.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daycare.app.backend.models.Caregiver;
import com.daycare.app.backend.repositories.CaregiverRepository;

@Service
public class CaregiverServiceImpl implements CaregiverService {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @Override
    public void save(Caregiver review) {
        caregiverRepository.save(review);
    }

    @Override
    public Optional<Caregiver> findById(Long id) {
        return caregiverRepository.findById(id);
    }

    @Override
    public Iterable<Caregiver> findAll() {
        return caregiverRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        caregiverRepository.deleteById(id);
        
    }
    
}
