package com.example.appent.service;

import com.example.appent.entity.BilletEntity;
import com.example.appent.exception.BilletInexistant;
import com.example.appent.helpers.BilletState;
import com.example.appent.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BilletService {
    private BilletRepository billetRepository;
    @Autowired
    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public BilletState getBilletState(long id) throws BilletInexistant {
        BilletEntity b = this.billetRepository.findByIdBillet(id);
        if (b == null) {
            throw new BilletInexistant(HttpStatus.NOT_FOUND);
        }
        return b.getEtat();
    }
}
