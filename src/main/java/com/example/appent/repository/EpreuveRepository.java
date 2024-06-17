package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.EpreuveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpreuveRepository extends JpaRepository<EpreuveEntity, Long> {
    Optional<EpreuveEntity> findByNom(String nom);
}
