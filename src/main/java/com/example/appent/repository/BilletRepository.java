package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BilletRepository extends JpaRepository<BilletEntity, Long> {
    BilletEntity findByIdBillet(Long idBillet);
}
