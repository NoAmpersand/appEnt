package com.example.appent.controller;


import com.example.appent.dto.BilletDto;
import com.example.appent.dto.SpectateurDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.SpectateurRepository;
import com.example.appent.service.SpectateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class SpectateurController {

    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private SpectateurService spectateurService;

    @GetMapping("/coucou")
    public String getCoucou(){
        return "coucou";
    }

    @PostMapping("/inscriptionSpectateur")
    public ResponseEntity<SpectateurEntity> inscription(@RequestBody SpectateurDto spectateurDto) {
        SpectateurEntity inscritSpectateur = spectateurService.inscription(spectateurDto);
        return new ResponseEntity<>(inscritSpectateur, HttpStatus.CREATED);
    }

    @GetMapping("/deleteUser")
    public SpectateurEntity deleteUser(@PathVariable Long id) {
        return spectateurService.deleteUtilisateur(id);
    }

    @PostMapping("/loginSpectateur")
    public ResponseEntity<String> logInUser(@RequestParam String email, @RequestParam String password){
        boolean isLoggedIn = spectateurService.connexion(email, password);
        if(isLoggedIn){
            return ResponseEntity.ok("Vous êtes connecté");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }

    /**
    @PostMapping("/{email}/reservation")
    public ResponseEntity<BilletEntity> reserverBillet(@PathVariable String email, @RequestBody BilletDto billetDto) {
        BilletEntity reservation = spectateurService.reserverBillet(email, billetDto);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
     */

    @DeleteMapping("/{email}/reservation/{id}")
    public ResponseEntity<Void> annulerReservation(@PathVariable String email, @PathVariable Long id){
        spectateurService.annulerReservation(email, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}