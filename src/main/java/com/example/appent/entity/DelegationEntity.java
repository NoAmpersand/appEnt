package com.example.appent.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private String medaillesOr;

    private String medaillesArgent;

    private String medaillesBronze;
}
