package com.example.appent.service;

import com.example.appent.entity.BilletEntity;
import com.example.appent.exceptions.BilletNotFoundException;
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
    public BilletState getBilletState(Long idBillet) throws BilletNotFoundException {

        BilletEntity bi = this.billetRepository.findByIdBillet(idBillet);

        if (bi == null)
            throw new BilletNotFoundException(HttpStatus.NOT_FOUND);

        return bi.getEtat();
    }

}
