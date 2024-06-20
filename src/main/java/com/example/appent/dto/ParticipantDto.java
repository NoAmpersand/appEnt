package com.example.appent.dto;

import com.example.appent.helpers.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appent.entity.ParticipantEntity}
 */
@Value
public class ParticipantDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Role role;
}