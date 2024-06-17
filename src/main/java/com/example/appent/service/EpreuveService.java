package com.example.appent.service;

import com.example.appent.entity.EpreuveEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.repository.EpreuveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EpreuveService {
    private EpreuveRepository epreuveRepository;

    public EpreuveService(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }


    public EpreuveEntity createEpreuve(EpreuveEntity epreuveEntity) throws EpreuveInexistante, EpreuveInexistante {
        // Vérifiez si une épreuve avec le même nom existe déjà
        if (epreuveRepository.findByNom(epreuveEntity.getNom()).isPresent()) {
            throw new EpreuveInexistante(HttpStatus.CONFLICT);
        }
        // Si aucune épreuve n'existe avec le même nom, sauvegardez la nouvelle épreuve
        return epreuveRepository.save(epreuveEntity);
    }
}
