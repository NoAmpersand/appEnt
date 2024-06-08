package com.example.appent.service;


import com.example.appent.repository.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpectateurService {
    private SpectateurRepository spectateurRepository;

    @Autowired
    public SpectateurService(SpectateurRepository spectateurRepository){
        this.spectateurRepository = spectateurRepository;
    }


}
