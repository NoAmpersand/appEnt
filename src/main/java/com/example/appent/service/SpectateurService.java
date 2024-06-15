package com.example.appent.service;


import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private SpectateurRepository spectateurRepository;

    @Autowired
    public SpectateurService(SpectateurRepository spectateurRepository){
        this.spectateurRepository = spectateurRepository;
    }


    public SpectateurEntity addUtilisateur(SpectateurEntity utilisateur) {
        return spectateurRepository.save(utilisateur);
    }

    public SpectateurEntity deleteUtilateur(Long id) {
         spectateurRepository.deleteById(id);
        return null;
    }


}
