package com.example.appent.entity;



import com.example.appent.helpers.ParticipantState;
import com.example.appent.helpers.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "participant")
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ParticipantState etat = ParticipantState.ACTIF;
    private Role role = Role.PARTICIPANT;
    @ManyToOne
    private DelegationEntity delegation;


}
