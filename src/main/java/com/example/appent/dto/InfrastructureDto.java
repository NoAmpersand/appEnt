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
    Long id;
    String nom;
    String adresse;
    Integer capacité;
}