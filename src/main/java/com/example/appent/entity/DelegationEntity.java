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

    @OneToMany(mappedBy = "delegation")
    private List<ParticipantEntity> participants = new ArrayList<>();

    public DelegationEntity(String nom){
        this.nom = nom;
        this.medaillesOr = 0;
        this.medaillesArgent =0;
        this.medaillesBronze = 0;
        this.participants = new ArrayList<>();

    }
}
