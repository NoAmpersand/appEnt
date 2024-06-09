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
    private Long id;

    private String nom;

    private String medaillesOr;

    private String medaillesArgent;

    private String medaillesBronze;

    @OneToMany(mappedBy = "delegation")
    private List<ParticipantEntity> participants = new ArrayList<>();
}
