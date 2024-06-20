package com.example.appent.service;

import com.example.appent.dto.ParticipantDto;
import com.example.appent.dto.SpectateurDto;
import com.example.appent.entity.DelegationEntity;
import com.example.appent.entity.ParticipantEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private DelegationRepository delegationRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, DelegationRepository delegationRepository) {
        this.participantRepository = participantRepository;
        this.delegationRepository = delegationRepository;
    }

    public ParticipantEntity deleteUtilisateur(Long id) {
        participantRepository.deleteById(id);
        return null;
    }

    public ParticipantEntity inscription(ParticipantDto participantDto, Long idD) {
        ParticipantEntity participant = new ParticipantEntity();
        participant.setFirstName(participantDto.getFirstName());
        participant.setLastName(participantDto.getLastName());
        participant.setEmail(participantDto.getEmail());
        participant.setPassword(participantDto.getPassword());

        // Ajout du participant dans une délégation
        DelegationEntity delegation = delegationRepository.findById(idD).get();
        participant.setDelegation(delegation);

        return participantRepository.save(participant);
    }

    public boolean connexion(String email, String password) {
        Optional<ParticipantEntity> spectateur = Optional.ofNullable(participantRepository.findByEmail(email));
        if (spectateur.isPresent() && spectateur.get().getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }


}
