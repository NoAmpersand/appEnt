package com.example.appent.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appent.entity.SpectateurEntity}
 */
@Value
public class SpectateurDto implements Serializable {
    String firstName;
    String lastName;
    String email;
    String password;
}