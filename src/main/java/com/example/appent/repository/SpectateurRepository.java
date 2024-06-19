package com.example.appent.repository;

import com.example.appent.entity.SpectateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectateurRepository extends JpaRepository<SpectateurEntity, Long> {


    SpectateurEntity findByEmail(String email);
}
