package com.example.appent.service;

import com.example.appent.dto.EpreuveDTO;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.InfrastructureEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.exception.OrganisateurNotExisting;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.repository.EpreuveRepository;
import com.example.appent.repository.InfrastructureSportiveRepository;
import com.example.appent.repository.OrganisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EpreuveService {
    private EpreuveRepository epreuveRepository;
    private InfrastructureSportiveRepository sportiveRepository;
    private OrganisateurRepository organisateurRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public EpreuveService(EpreuveRepository epreuveRepository, InfrastructureSportiveRepository sportiveRepository, OrganisateurRepository organisateurRepository) {

        this.epreuveRepository = epreuveRepository;
        this.sportiveRepository = sportiveRepository;
        this.organisateurRepository = organisateurRepository;
    }


    public EpreuveDTO createEpreuve(EpreuveDTO epreuveDTO, String mail) throws EpreuveInexistante, EpreuveInexistante {
        // Vérifiez si une épreuve avec le même nom existe déjà
        if (this.epreuveRepository.findByNom(epreuveDTO.getNom()).isPresent()) {
            throw new EpreuveInexistante(HttpStatus.CONFLICT);
        }
        if(this.organisateurRepository.findByEmail(mail) == null){
            throw new OrganisateurNotExisting("Ce organisateur n'existe pas");
        }

        InfrastructureEntity infrastructureEntity = this.sportiveRepository.findById(epreuveDTO.getInsfrastructureSportiveId()).get();

        if(epreuveDTO.getNbPlacesSpectateur()>infrastructureEntity.getCapacity()){
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

         this.epreuveRepository.save(epreuveEntity);
         return this.modelMapper.map(epreuveEntity, EpreuveDTO.class);
    }

    public ResponseEntity<String> deleteEpreuve(Long idEpreuve) throws EpreuveInexistante {
        if(this.epreuveRepository.findById(idEpreuve).isEmpty()) {
            throw new EpreuveInexistante(HttpStatus.NOT_FOUND);
        }
        EpreuveEntity epreuve = this.epreuveRepository.findById(idEpreuve).get();
        this.epreuveRepository.deleteById(idEpreuve);

        return new ResponseEntity<>("Epreuve supprimé", HttpStatus.OK);
    }


}
