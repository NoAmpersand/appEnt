package com.example.appent.repository;

import com.example.appent.entity.OrganisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisateurRepository extends JpaRepository<OrganisateurEntity, Long> {
    public OrganisateurEntity findByEmail(String email);
}
