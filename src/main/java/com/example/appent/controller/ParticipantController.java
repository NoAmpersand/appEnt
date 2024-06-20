package com.example.appent.controller;


import com.example.appent.dto.BilletDto;
import com.example.appent.dto.ParticipantDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.ParticipantEntity;
import com.example.appent.repository.ParticipantRepository;
import com.example.appent.service.EpreuveService;
import com.example.appent.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @Autowired
    private EpreuveService epreuveService;
    @Autowired
    private ParticipantRepository participantRepository;

    @PostMapping("/inscriptionParticipant")
    public ResponseEntity<ParticipantEntity> inscription(@RequestParam Long idDelegation, @RequestBody ParticipantDto participantDto ) {
        ParticipantEntity inscritSpectateur = participantService.inscription(participantDto, idDelegation);
        return new ResponseEntity<>(inscritSpectateur, HttpStatus.CREATED);
    }

    @PostMapping("/loginParticipant")
    public ResponseEntity<String> connexion(@RequestParam String email, @RequestParam String password) {
        boolean isLoggedIn = participantService.connexion(email, password);
        if(isLoggedIn){
            return ResponseEntity.ok("Vous êtes connecté");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    @PostMapping("/{email}/participationConfirm")
    public ResponseEntity<String> entregistrerParticipation(@PathVariable String email, @RequestParam Long epreuveId) {
        Long idParticipant = participantRepository.findByEmail(email).getId();
        String participation = epreuveService.registerParticipant(epreuveId, idParticipant);
        return new ResponseEntity<>(participation, HttpStatus.CREATED);
    }

    @PostMapping("/{email}/participationRemove")
    public ResponseEntity<String> annulerParticipation(@PathVariable String email, @RequestParam Long epreuveId) {
        Long idParticipant = participantRepository.findByEmail(email).getId();
        String participation = epreuveService.unregisterParticipant(epreuveId, idParticipant);
        return new ResponseEntity<>(participation, HttpStatus.CREATED);
    }
}
