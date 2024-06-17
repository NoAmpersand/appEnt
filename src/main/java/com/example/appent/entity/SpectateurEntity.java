package com.example.appent.entity;

import com.example.appent.helpers.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "spectateur")
public class SpectateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role=Role.SPECTATEUR;
    private int  money;

    @OneToMany(mappedBy = "spectateur")
    private ArrayList<BilletEntity> billets;

}