package com.example.appent.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "infrastructure")
public class InfrastructureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String adresse;

    private Integer capacity;

    @OneToMany(mappedBy = "insfrastructureSportive")
    private Collection<EpreuveEntity> epreuveEntities;

    public InfrastructureEntity(String nom, int capacite, String adresse) {
        this.nom = nom;
        this.capacity = capacite ;
        this.adresse = adresse ;
    }



}
