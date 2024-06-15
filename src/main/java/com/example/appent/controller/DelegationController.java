package com.example.appent.controller;

import com.example.appent.entity.DelegationEntity;
import com.example.appent.exception.DelegationExist;
import com.example.appent.exception.DelegationNotExisting;
import com.example.appent.service.DelegationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DelegationController {

    @Autowired
    private final DelegationService delegationService;

    @PostMapping("/createDelegation")
    public DelegationEntity createDelegation(@RequestBody String nom) throws DelegationExist {
        return delegationService.createDelegation(nom);
    }

    @DeleteMapping("/supprimer")
    public void DeleteDelegation(@RequestParam String nom) throws DelegationNotExisting {
        delegationService.DeleteDelegation(nom);
    }

}
