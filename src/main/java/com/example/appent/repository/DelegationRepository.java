package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.DelegationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<DelegationEntity, Long> {
}
