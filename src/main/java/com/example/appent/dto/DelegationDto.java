package com.example.appent.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.appent.entity.DelegationEntity}
 */
@Value
public class DelegationDto implements Serializable {
    String nom;
    int medaillesOr;
    int medaillesArgent;
    int medaillesBronze;
}