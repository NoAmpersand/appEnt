package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.helpers.BilletState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilletRepository extends JpaRepository<BilletEntity, Long> {

    List<BilletEntity> findByState(BilletState etat);

    List<BilletEntity> findBySpectatpr(SpectateurEntity spectateur);

    List<BilletEntity> findBySpectatorId(Long spectateurId);

    List<BilletEntity> findByPriceLessThan(float prix);

    List<BilletEntity> findByPriceGreaterThan(float prix);

    List<BilletEntity> findByPriceBetween(float minPrice, float maxPrice);

    long countByState(BilletState etat);

    void deleteByState(BilletState etat);
}
