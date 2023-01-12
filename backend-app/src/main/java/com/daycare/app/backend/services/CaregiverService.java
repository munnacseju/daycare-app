package com.daycare.app.backend.services;

import java.util.Optional;

import com.daycare.app.backend.models.Caregiver;

public interface CaregiverService {
        void save(Caregiver review);
        Optional<Caregiver> findById(Long id);
        Iterable<Caregiver> findAll();
        void deleteById(Long id);
}
