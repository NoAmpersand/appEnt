package com.example.appent.dto;

import com.example.appent.helpers.BilletState;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.appent.entity.BilletEntity}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BilletDto implements Serializable {

    private float prix;
    private BilletState state;

    private Long sId;
    private Long epId;

}