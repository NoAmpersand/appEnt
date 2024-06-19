package com.example.appent.controller;

import com.example.appent.dto.ControlleurEntityDto;
import com.example.appent.service.ControlleurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ControlleurController {

    private final ControlleurService controlleurService;

    @Autowired
    public ControlleurController(ControlleurService controlleurService) {
        this.controlleurService = controlleurService;
    }

    @PostMapping("/inscriptionControlleur")
    public ResponseEntity<?> inscription(@RequestBody ControlleurEntityDto controlleurDto) {
        try {
            controlleurService.inscription(controlleurDto);
            return ResponseEntity.ok("Inscription réussie");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'inscription: " + e.getMessage());
        }
    }

    @PostMapping("/connexionControlleur")
    public ResponseEntity<?> connexion(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = controlleurService.connexion(email, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Connexion réussie");
        } else {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }
    }

    @PostMapping("/validerBillet/{billetId}")
    public ResponseEntity<?> validerBillet(@PathVariable Long billetId) {
        try {
            controlleurService.validerBillet(billetId);
            return ResponseEntity.ok("Billet validé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la validation du billet: " + e.getMessage());
        }
    }
}
