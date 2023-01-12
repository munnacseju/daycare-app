package com.daycare.app.backend.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.daycare.app.backend.models.Caregiver;
public interface CaregiverRepository extends CrudRepository<Caregiver, Long>{
    Optional<Caregiver> findById(Long id);
    Iterable<Caregiver> findAll();
}

