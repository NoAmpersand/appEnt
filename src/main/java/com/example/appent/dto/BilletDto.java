package com.example.appent.dto;

import com.example.appent.helpers.BilletState;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.appent.entity.BilletEntity}
 */
@Value
public class BilletDto implements Serializable {
    float prix;
    BilletState etat;
    SpectateurDto spectateur;
    EpreuveDTO epreuve;
    private LocalDateTime dateAchat;
    private float remboursement;
}