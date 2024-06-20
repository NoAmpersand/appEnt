package com.example.appent.service;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.ControlleurEntity;
import com.example.appent.entity.OrganisateurEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.exception.BilletInexistant;
import com.example.appent.helpers.BilletState;
import com.example.appent.repository.BilletRepository;
import com.example.appent.repository.ControlleurRepository;
import com.example.appent.repository.OrganisateurRepository;
import com.example.appent.repository.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilletService {
    private BilletRepository billetRepository;

    private SpectateurRepository spectateurRepository;

    private ControlleurRepository organisateurRepository;

    @Autowired
    public BilletService(ControlleurRepository organisateurRepository,BilletRepository billetRepository, SpectateurRepository spectateurRepository) {
        this.billetRepository = billetRepository;
        this.spectateurRepository= spectateurRepository;
        this.organisateurRepository= organisateurRepository;
    }

    public BilletState getBilletState(long id) throws BilletInexistant {
        BilletEntity b = this.billetRepository.findByIdBillet(id);
        if (b == null) {
            throw new BilletInexistant(HttpStatus.NOT_FOUND);
        }
        return b.getEtat();
    }

    public List<BilletEntity> recupererTousLesBilletsUtil(String mail){
        SpectateurEntity spec = spectateurRepository.findByEmail((mail));
        return billetRepository.findAllBySpectateur(spec);
    }

}