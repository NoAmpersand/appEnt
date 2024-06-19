package com.example.appent.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appent.entity.InfrastructureEntity}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfrastructureDto implements Serializable {
    private  String nom;
    private String adresse;
    private int capacite;
}