package com.example.appent.repository;

import com.example.appent.entity.OrganisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrganisateurRepository extends JpaRepository<OrganisateurEntity, Long> {
    public OrganisateurEntity findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OrganisateurEntity o WHERE o.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
