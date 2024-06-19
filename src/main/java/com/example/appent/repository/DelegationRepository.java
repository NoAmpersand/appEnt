package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.DelegationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelegationRepository extends JpaRepository<DelegationEntity, Long> {
    public DelegationEntity findByNom(String nom);
}
