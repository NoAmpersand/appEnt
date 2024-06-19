package com.example.appent.service;


import com.example.appent.dto.BilletDto;
import com.example.appent.dto.SpectateurDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.BilletRepository;
import com.example.appent.repository.EpreuveRepository;
import com.example.appent.repository.InfrastructureSportiveRepository;
import com.example.appent.repository.SpectateurRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpectateurService {
    private SpectateurRepository spectateurRepository;
    private EpreuveRepository epreuveRepository;
    private BilletRepository billetRepository;

    @Autowired
    public SpectateurService(SpectateurRepository spectateurRepository, EpreuveRepository epreuveRepository, BilletRepository billetRepository){
        this.spectateurRepository = spectateurRepository;
        this.epreuveRepository = epreuveRepository;
        this.billetRepository = billetRepository;
    }

    public SpectateurEntity deleteUtilateur(Long id) {
         spectateurRepository.deleteById(id);
        return null;
    }

    public SpectateurEntity inscription(SpectateurDto spectateurDto) {
        SpectateurEntity spectateur = new SpectateurEntity();
        spectateur.setFirstName(spectateurDto.getFirstName());
        spectateur.setLastName(spectateurDto.getLastName());
        spectateur.setEmail(spectateurDto.getEmail());
        spectateur.setPassword(spectateurDto.getPassword());
        return spectateurRepository.save(spectateur);
    }

    public boolean connexion(String email, String password) {
        Optional<SpectateurEntity> spectateur = Optional.ofNullable(spectateurRepository.findByEmail(email));
        if (spectateur.isPresent() && spectateur.get().getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public BilletEntity reserverBillet(String email, BilletDto billetDto) {
        SpectateurEntity spectateur = spectateurRepository.findByEmail(email);
        EpreuveEntity epreuve = epreuveRepository.findById(billetDto.getEpreuve().getIdEpreuve()).orElseThrow();

        BilletEntity billet = new BilletEntity();
        billet.setSpectateur(spectateur);
        billet.setEpreuve(epreuve);


        return billetRepository.save(billet);
    }

    public void annulerReservation(String email, Long id) {
        BilletEntity billet = billetRepository.findById(id).orElseThrow();
        if(billet.getSpectateur().getEmail().equals(email)) {
            billetRepository.delete(billet);
        }
    }
}
