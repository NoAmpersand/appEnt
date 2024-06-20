package com.example.appent.service;

import com.example.appent.dto.EpreuveDTO;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.InfrastructureEntity;
import com.example.appent.entity.ParticipantEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.exception.OrganisateurNotExisting;
import com.example.appent.helpers.EpreuveState;
import com.example.appent.helpers.ParticipantState;
import com.example.appent.repository.EpreuveRepository;
import com.example.appent.repository.InfrastructureSportiveRepository;
import com.example.appent.repository.OrganisateurRepository;
import com.example.appent.repository.ParticipantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpreuveService {
    private EpreuveRepository epreuveRepository;
    private InfrastructureSportiveRepository sportiveRepository;
    private OrganisateurRepository organisateurRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private final ParticipantRepository participantRepository;

    public EpreuveService(EpreuveRepository epreuveRepository, InfrastructureSportiveRepository sportiveRepository, OrganisateurRepository organisateurRepository,
                          ParticipantRepository participantRepository) {

        this.epreuveRepository = epreuveRepository;
        this.sportiveRepository = sportiveRepository;
        this.organisateurRepository = organisateurRepository;
        this.participantRepository = participantRepository;
    }

    public List<EpreuveEntity> getEpreuvesDisponibles() {
        return epreuveRepository.findAll().stream()
                .filter(epreuve -> epreuve.getDisponibilité() == EpreuveState.DISPONIBLE)
                .collect(Collectors.toList());
    }

    public String registerParticipant(Long eventId, Long participantId) {
        Optional<EpreuveEntity> optionalEpreuve = epreuveRepository.findById(eventId);
        Optional<ParticipantEntity> optionalParticipant = participantRepository.findById(participantId);

        if (optionalEpreuve.isPresent() && optionalParticipant.isPresent()) {
            EpreuveEntity epreuve = optionalEpreuve.get();
            ParticipantEntity participant = optionalParticipant.get();

            if (EpreuveState.INDISPONIBLE.equals(epreuve.getDisponibilité())) {
                    return "Epreuve indisponible à l'inscription";
            }

            Timestamp now = new Timestamp(System.currentTimeMillis());
            long diff = epreuve.getDate().getTime() - now.getTime();
            long joursAvantEpreuve = diff / (1000 * 60 * 60 * 24);

            if (joursAvantEpreuve < 10) {
                return "Inscription fermée";
            }
            epreuve.getParticipants().add(participant);
            epreuveRepository.save(epreuve);

            return "Inscription confirmée";
        }
        return "Epreuve ou participant introuvable";
    }
    public String unregisterParticipant(Long eventId, Long participantId) {
        Optional<EpreuveEntity> optionalEpreuve = epreuveRepository.findById(eventId);
        Optional<ParticipantEntity> optionalParticipant = participantRepository.findById(participantId);

        if (optionalEpreuve.isPresent() && optionalParticipant.isPresent()) {
            EpreuveEntity epreuve = optionalEpreuve.get();
            ParticipantEntity participant = optionalParticipant.get();

            Timestamp now = new Timestamp(System.currentTimeMillis());
            long diff = epreuve.getDate().getTime() - now.getTime();
            long joursAvantEpreuve = diff / (1000 * 60 * 60 * 24);

            if (joursAvantEpreuve < 10) {
                participant.setEtat(ParticipantState.FORFAIT);
                participantRepository.save(participant);
                return "Participant marqué comme forfait";
            } else {
                epreuve.getParticipants().remove(participant);
                epreuveRepository.save(epreuve);
                return "Désinscription confirmée";
            }
        }
        return "Epreuve ou participant introuvable";
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
                EpreuveState.DISPONIBLE,
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
    public List<EpreuveEntity> recupererToutesLesEpreuves() {
        return epreuveRepository.findAll();
    }

}
