package com.example.appent.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resultat")
public class ResultatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResultat;

    @ManyToOne
    private EpreuveEntity epreuve;

    @ManyToOne
    private ParticipantEntity participant;

    private int points;

    private int position;

}
