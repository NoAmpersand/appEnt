package com.example.appent.controller;

import com.example.appent.dto.OrganisateurDTO;
import com.example.appent.exception.OrganisateurAlreadyExistsException;
import com.example.appent.service.DelegationService;
import com.example.appent.service.OrganisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class OrganisateurController {

    private final OrganisateurService organisateurService;
    private final DelegationService delegationService;

    @Autowired
    public OrganisateurController(OrganisateurService organisateurService, DelegationService delegationService) {
        this.organisateurService = organisateurService;
        this.delegationService = delegationService;
    }

    /**
     *
     * @param organisateurDTO
     * @return
     * @throws OrganisateurAlreadyExistsException
     */
    @PostMapping("/createOrganisateur")
    public OrganisateurDTO createOrganisateur(@RequestBody OrganisateurDTO organisateurDTO) throws OrganisateurAlreadyExistsException {
        return this.organisateurService.createOrganisateur(organisateurDTO);
    }



}
