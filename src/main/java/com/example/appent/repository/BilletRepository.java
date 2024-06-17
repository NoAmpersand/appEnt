package com.example.appent.repository;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.EpreuveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface BilletRepository extends JpaRepository<BilletEntity, Long> {
    BilletEntity findByIdBillet(Long idBillet);

    BilletEntity findByIdBilletAndSpectateurId(Long idBillet, Long idS);

    Collection<BilletEntity> findAllBySpectateurId(Long idSpectateur) ;

}
