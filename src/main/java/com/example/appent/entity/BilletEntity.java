package com.example.appent.entity;

import com.example.appent.helpers.BilletState;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "billet")
public class BilletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBillet;

    private float prix;

    @Enumerated(EnumType.STRING)
    private BilletState etat ;

    @ManyToOne
    private SpectateurEntity spectateur;

    @ManyToOne
    private EpreuveEntity epreuve;
}