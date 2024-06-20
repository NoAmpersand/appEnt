package com.example.appent.service;

import com.example.appent.dto.DelegationDto;
import com.example.appent.dto.ResultatDto;
import com.example.appent.entity.ResultatEntity;
import com.example.appent.entity.DelegationEntity;
import com.example.appent.entity.ParticipantEntity;
import com.example.appent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    @Autowired
    private final ResultatRepository resultatRepository;
    @Autowired
    private final DelegationRepository delegationRepository;
    @Autowired
    private final ParticipantRepository participantRepository;
    @Autowired
    private final EpreuveRepository epreuveRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;

    @Autowired
    public ResultatService(ResultatRepository resultatRepository, DelegationRepository delegationRepository, ParticipantRepository participantRepository,
                           EpreuveRepository epreuveRepository) {
        this.resultatRepository = resultatRepository;
        this.delegationRepository = delegationRepository;
        this.participantRepository = participantRepository;
        this.epreuveRepository = epreuveRepository;
    }

    @Transactional
    public void publierResultats(String emailOrganisateur, List<ResultatDto> resultatsDTO) {
        if (!organisateurRepository.existsByEmail(emailOrganisateur)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Personne non autorisée");
        }
        for (ResultatDto dto : resultatsDTO) {
            ResultatEntity resultat = new ResultatEntity();
            resultat.setEpreuve(epreuveRepository.findById(dto.getEpreuve().getIdEpreuve()).orElseThrow());
            ParticipantEntity participant = participantRepository.findById(dto.getParticipant().getId()).orElseThrow();
            resultat.setParticipant(participant);
            resultat.setPoints(dto.getPoints());
            resultat.setPosition(dto.getPosition());
            resultatRepository.save(resultat);
            DelegationEntity delegation = participant.getDelegation();
            switch (dto.getPosition()) {
                case 1:
                    delegation.setMedaillesOr(delegation.getMedaillesOr() + 1);
                    break;
                case 2:
                    delegation.setMedaillesArgent(delegation.getMedaillesArgent() + 1);
                    break;
                case 3:
                    delegation.setMedaillesBronze(delegation.getMedaillesBronze() + 1);
                    break;
                default:
                    break;
            }
            delegationRepository.save(delegation);
        }
    }

    public List<DelegationDto> getClassementGeneral() {
        List<DelegationEntity> delegations = delegationRepository.findAll();
        return delegations.stream()
                .sorted((d1, d2) -> {
                    int compare = Integer.compare(d2.getMedaillesOr(), d1.getMedaillesOr());
                    if (compare != 0) return compare;
                    compare = Integer.compare(d2.getMedaillesArgent(), d1.getMedaillesArgent());
                    if (compare != 0) return compare;
                    return Integer.compare(d2.getMedaillesBronze(), d1.getMedaillesBronze());
                })
                .map(delegation -> new DelegationDto(
                        delegation.getNom(),
                        delegation.getMedaillesOr(),
                        delegation.getMedaillesArgent(),
                        delegation.getMedaillesBronze()))
                .collect(Collectors.toList());
    }
}
