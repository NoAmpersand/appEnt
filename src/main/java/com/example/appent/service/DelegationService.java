package com.example.appent.service;

import com.example.appent.entity.DelegationEntity;
import com.example.appent.exception.DelegationEmpty;
import com.example.appent.exception.DelegationExist;
import com.example.appent.exception.DelegationNotExisting;
import com.example.appent.exception.OrganisateurNotExisting;
import com.example.appent.repository.ControlleurRepository;
import com.example.appent.repository.DelegationRepository;
import com.example.appent.repository.OrganisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DelegationService {
    private DelegationRepository delegationRepository;
    private OrganisateurRepository organisateurRepository;

    @Autowired
    public DelegationService(DelegationRepository delegationRepository, OrganisateurRepository organisateurRepository) {
        this.delegationRepository = delegationRepository;
        this.organisateurRepository = organisateurRepository;
    }

    public DelegationEntity createDelegation(String nom, String mail) throws DelegationExist, OrganisateurNotExisting {
        DelegationEntity delegation = this.delegationRepository.findByNom(nom);
        if(delegation != null) {
            throw new DelegationExist();
        }
        if(this.organisateurRepository.findByEmail(mail) == null) {
            throw new OrganisateurNotExisting("Organisateur n'existe pas");
        }
        delegation= new DelegationEntity(nom);
        return this.delegationRepository.save(delegation);
    }

    public ResponseEntity<String> DeleteDelegation(String nom, String mail) throws DelegationNotExisting, OrganisateurNotExisting {
        DelegationEntity delegation = this.delegationRepository.findByNom(nom);
        if (delegation == null) {
            throw new DelegationNotExisting();
        }
        if(this.organisateurRepository.findByEmail(mail) == null) {
            throw new OrganisateurNotExisting("Organisateur n'existe pas");
        }
        this.delegationRepository.delete(delegation);
        return new ResponseEntity<>("Delegation " +nom +" supprimée", HttpStatus.OK);
    }


    public List<DelegationEntity> getDelegation() throws DelegationEmpty {
        List<DelegationEntity> listDelegation = this.delegationRepository.findAll();
        if(listDelegation.isEmpty()) {
            throw new DelegationEmpty();
        }
        return listDelegation;
    }

    public List<DelegationEntity> recupererToutesLesDelegations() {
        return delegationRepository.findAll();
    }

}
