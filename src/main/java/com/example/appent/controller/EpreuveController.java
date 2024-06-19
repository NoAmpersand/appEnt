package com.example.appent.controller;

import com.example.appent.dto.EpreuveDTO;
import com.example.appent.entity.OrganisateurEntity;
import com.example.appent.exception.EpreuveInexistante;
import com.example.appent.repository.OrganisateurRepository;
import com.example.appent.service.EpreuveService;
import com.example.appent.service.OrganisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
