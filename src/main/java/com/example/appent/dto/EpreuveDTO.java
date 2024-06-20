package com.example.appent.dto;

import com.example.appent.helpers.EpreuveState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
public class EpreuveDTO {
        private Long idEpreuve;

        private String nom ;

        private Timestamp date ;

        private int nbPlacesSpectateur ;

        private int nbParticipants ;

        private EpreuveState disponibilité;

        private Long insfrastructureSportiveId ;

    }

