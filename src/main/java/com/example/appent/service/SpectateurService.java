package com.example.appent.service;


import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpectateurService {
    private SpectateurRepository spectateurRepository;

    @Autowired
    public SpectateurService(SpectateurRepository spectateurRepository){
        this.spectateurRepository = spectateurRepository;
    }


    public SpectateurEntity ajouterUtilisateur(SpectateurEntity utilisateur) {
        return spectateurRepository.save(utilisateur);
    }

    // Nouvelle méthode pour récupérer tous les utilisateurs
    public List<SpectateurEntity> getAllUsers() {
        return spectateurRepository.findAll();
    }

}
