package com.example.appent.dto;

import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.ParticipantEntity;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appent.entity.ResultatEntity}
 */
@Value
public class ResultatDto implements Serializable {
    EpreuveEntity epreuve;
    ParticipantEntity participant;
    int points;
    int position;
}