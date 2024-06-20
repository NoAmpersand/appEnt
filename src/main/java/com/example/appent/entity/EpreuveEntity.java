package com.example.appent.entity;

import com.example.appent.helpers.EpreuveState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private int nbPlacesSpectateur;

    private int nbParticipants;

    private EpreuveState disponibilité;

    @ManyToOne
    private InfrastructureEntity insfrastructureSportive ;

    @OneToMany(mappedBy = "epreuve")
    private List<BilletEntity> billets;

    @OneToMany(mappedBy = "epreuve")
    private Collection<ResultatEntity> listeResultats ;

    @OneToMany
    private Collection<ParticipantEntity> participants;

    public EpreuveEntity(String nom, Timestamp date, int nbPlacesSpectateur, int nbParticipants, EpreuveState disponibilité, InfrastructureEntity insfrastructureSportive) {
        this.nom = nom;
        this.date = date;
        this.nbPlacesSpectateur = nbPlacesSpectateur;
        this.nbParticipants = nbParticipants;
        this.disponibilité = disponibilité;
        this.insfrastructureSportive = insfrastructureSportive;
        listeResultats = new ArrayList<>();
        participants = new ArrayList<>();
    }
}