package com.example.appent.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delegation")
@Getter
public class DelegationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDelegation;

    private String nom;

    private int medaillesOr;

    private int medaillesArgent;

    private int medaillesBronze;

    @OneToMany(mappedBy = "delegation",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipantEntity> participants;

    public DelegationEntity(String nom){
        this.nom = nom;
        this.medaillesOr = 0;
        this.medaillesArgent =0;
        this.medaillesBronze = 0;
        this.participants = new ArrayList<>();
    }
    // Ajout d'un constructeur pour supporter la conversion en DTO
    public DelegationEntity(String nom, int medaillesOr, int medaillesArgent, int medaillesBronze) {
        this.nom = nom;
        this.medaillesOr = medaillesOr;
        this.medaillesArgent = medaillesArgent;
        this.medaillesBronze = medaillesBronze;
    }
}
