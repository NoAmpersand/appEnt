package com.example.appent.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appent.entity.ControlleurEntity}
 */
@Value
public class ControlleurEntityDto implements Serializable {
    String firstName;
    String lastName;
    String email;
}