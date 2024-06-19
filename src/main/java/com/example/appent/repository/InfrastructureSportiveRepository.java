package com.example.appent.repository;

import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.InfrastructureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfrastructureSportiveRepository extends JpaRepository<InfrastructureEntity, Long> {
    Optional<InfrastructureEntity> findById(Long id);

    Optional<InfrastructureEntity> findByNom(String nom);



}
