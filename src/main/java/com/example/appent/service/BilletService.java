package com.example.appent.service;

import com.example.appent.entity.BilletEntity;
import com.example.appent.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilletService {
    private BilletRepository billetRepository;
    @Autowired
    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }



}
