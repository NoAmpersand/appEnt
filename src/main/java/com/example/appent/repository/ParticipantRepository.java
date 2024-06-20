package com.example.appent.repository;

import com.example.appent.entity.ParticipantEntity;
import com.example.appent.entity.SpectateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    ParticipantEntity findByEmail(String email);
}
