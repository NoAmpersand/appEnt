package com.example.appent.service;

import com.example.appent.dto.EpreuveDTO;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.InfrastructureEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.repository.EpreuveRepository;
import com.example.appent.repository.InfrastructureSportiveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EpreuveService {
    private EpreuveRepository epreuveRepository;
    private InfrastructureSportiveRepository sportiveRepository;

    public EpreuveService(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }


    public EpreuveEntity createEpreuve(EpreuveDTO epreuveDTO) throws EpreuveInexistante, EpreuveInexistante {
        // Vérifiez si une épreuve avec le même nom existe déjà
        if (this.epreuveRepository.findByNom(epreuveDTO.getNom()).isPresent()) {
            throw new EpreuveInexistante(HttpStatus.CONFLICT);
        }
        if(this.sportiveRepository.findById(epreuveDTO.getIdEpreuve()).isPresent() ){
            // JETTER ERREUR
        }

        InfrastructureEntity infrastructureEntity = this.sportiveRepository.findById(epreuveDTO.getInsfrastructureSportiveId()).get();

        if(epreuveDTO.getNbPlacesSpectateur()>infrastructureEntity.getCapacité()){
            throw new EpreuveInexistante(HttpStatus.CONFLICT);
        }

        // Si aucune épreuve n'existe avec le même nom, sauvegardez la nouvelle épreuve
        EpreuveEntity epreuveEntity = new EpreuveEntity(
                epreuveDTO.getNom(),
                epreuveDTO.getDate(),
                epreuveDTO.getNbPlacesSpectateur(),
                epreuveDTO.getNbParticipants(),
                infrastructureEntity
        );
        return epreuveRepository.save(epreuveEntity);
    }
}
