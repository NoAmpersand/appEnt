package com.example.appent.controller;

import com.example.appent.entity.DelegationEntity;
import com.example.appent.exception.DelegationEmpty;
import com.example.appent.exception.DelegationExist;
import com.example.appent.exception.DelegationNotExisting;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.service.DelegationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DelegationController {

    @Autowired
    private final DelegationService delegationService;

    @PostMapping("/createDelegation/{email}")
    public DelegationEntity createDelegation(@RequestBody String nom, @PathVariable String email) throws DelegationExist {
        return delegationService.createDelegation(nom, email);
    }

    @DeleteMapping("/deleteDelegation/{email}")
    public ResponseEntity<String> DeleteDelegation(@RequestBody String nom, @PathVariable String email) throws DelegationNotExisting {
        return delegationService.DeleteDelegation(nom, email);
    }

    @GetMapping("/delegations")
    public ResponseEntity<List<DelegationEntity>> recupererToutesLesDelegations() {
        List<DelegationEntity> delegations = delegationService.recupererToutesLesDelegations();
        return ResponseEntity.ok(delegations);
    }


}
