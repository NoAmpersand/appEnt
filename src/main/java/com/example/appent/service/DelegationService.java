package com.example.appent.service;

import com.example.appent.entity.DelegationEntity;
import com.example.appent.exception.DelegationExist;
import com.example.appent.exception.DelegationNotExisting;
import com.example.appent.repository.DelegationRepository;
import org.springframework.stereotype.Service;

@Service
public class DelegationService {
    private DelegationRepository delegationRepository;

    public DelegationService(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }

    public DelegationEntity createDelegation(String nom ) throws DelegationExist {
        DelegationEntity delegation = this.delegationRepository.findByNom(nom);
        if(delegation == null) {
            throw new DelegationExist();
        }
        delegation= new DelegationEntity(nom);
        return this.delegationRepository.save(delegation);
    }

    public void DeleteDelegation(String nom) throws DelegationNotExisting {
        DelegationEntity delegation = this.delegationRepository.findByNom(nom);
        if (delegation == null) {
            throw new DelegationNotExisting();
        }
        this.delegationRepository.delete(delegation);
    }
}
