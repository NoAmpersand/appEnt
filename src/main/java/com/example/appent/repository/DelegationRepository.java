package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelegationRepository extends JpaRepository<BilletEntity, Long> {
}
