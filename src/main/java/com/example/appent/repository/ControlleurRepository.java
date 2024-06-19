package com.example.appent.repository;

import com.example.appent.entity.ControlleurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlleurRepository extends JpaRepository<ControlleurEntity, Long> {
    ControlleurEntity findByEmail(String email);
}
