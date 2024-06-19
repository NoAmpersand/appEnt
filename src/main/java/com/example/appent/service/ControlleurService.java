package com.example.appent.service;

import com.example.appent.dto.BilletDto;
import com.example.appent.dto.ControlleurEntityDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.ControlleurEntity;
import com.example.appent.helpers.BilletState;
import com.example.appent.helpers.Role;
import com.example.appent.repository.BilletRepository;
import com.example.appent.repository.ControlleurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ControlleurService {
    private final ControlleurRepository controlleurRepository;
    private final BilletRepository billetRepository;

    @Autowired
    public ControlleurService(ControlleurRepository controlleurRepository, BilletRepository billetRepository) {
        this.controlleurRepository = controlleurRepository;
        this.billetRepository = billetRepository;
    }

    public ControlleurEntity inscription(ControlleurEntityDto controlleurDto) {
        ControlleurEntity controlleur = new ControlleurEntity();
        controlleur.setFirstName(controlleurDto.getFirstName());
        controlleur.setLastName(controlleurDto.getLastName());
        controlleur.setEmail(controlleurDto.getEmail());
        controlleur.setRole(Role.CONTROLEUR);
        return controlleurRepository.save(controlleur);
    }

    public boolean connexion(String email, String password) {
        Optional<ControlleurEntity> controlleur = Optional.ofNullable(controlleurRepository.findByEmail(email));
        return controlleur.isPresent() && controlleur.get().getPassword().equals(password);
    }

    public void validerBillet(Long billetId) {
        BilletEntity billet = billetRepository.findById(billetId).orElseThrow();
        if (billet.getEtat() == BilletState.VALID) {
            billet.setEtat(BilletState.USED);
            billetRepository.save(billet);
        } else {
            throw new RuntimeException("Billet invalide ou déjà utilisé.");
        }
    }
}
