package com.example.appent.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Participant")
public class ParticipantEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String delegation;


}
