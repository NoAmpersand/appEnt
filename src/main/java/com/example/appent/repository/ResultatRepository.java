package com.example.appent.repository;

import com.example.appent.entity.ResultatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends JpaRepository<ResultatEntity, Long> {
}
