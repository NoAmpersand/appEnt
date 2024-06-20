package com.example.appent.controller;

import com.example.appent.dto.EpreuveDTO;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.OrganisateurEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.repository.OrganisateurRepository;
import com.example.appent.service.EpreuveService;
import com.example.appent.service.OrganisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/epreuve")
public class EpreuveController {
    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private OrganisateurService organisateurService;

    private OrganisateurRepository organisateurRepository;

    @PostMapping("/createEpreuve/{mail}")
    public EpreuveDTO createEpreuve(@PathVariable("mail") String mail, @RequestBody EpreuveDTO epreuveDTO) throws EpreuveInexistante {
        return epreuveService.createEpreuve(epreuveDTO, mail);
    }

    @GetMapping("/listepreuve")
    public ResponseEntity<List<EpreuveEntity>> recupererToutesLesEpreuves() {
        List<EpreuveEntity> spectateurs = epreuveService.recupererToutesLesEpreuves();
        return ResponseEntity.ok(spectateurs);
    }
    @DeleteMapping("/deleteEpreuve/{mail}")
    public ResponseEntity<?> deleteEpreuve(@PathVariable("mail") String mail, @RequestParam Long id) throws EpreuveInexistante {
        epreuveService.deleteEpreuve(id);
        return ResponseEntity.ok("Epreuve supprimée avec succès");
    }

}
