package com.example.appent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "epreuve")
@Getter

public class EpreuveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEpreuve;

    private String nom;

    private Timestamp date;

    @ManyToOne
    private InfrastructureEntity insfrastructureSportive ;

    private int nbPlacesSpectateur;

    private int nbParticipants;

    // TABPLACE


}