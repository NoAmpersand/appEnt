package com.example.appent.controller;


import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.SpectateurRepository;
import com.example.appent.service.SpectateurService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SpectateurController {

    @Autowired
    private SpectateurRepository specteurRepository;
    @Autowired
    private SpectateurService spectateurService;

    @GetMapping("/coucou")
    public String getCoucou(){
        return "coucou";
    }

    @PostMapping("/createUser")
    public SpectateurEntity createUser(@RequestBody SpectateurEntity spectateur){
        return specteurRepository.save(spectateur);
    }

    @GetMapping("/deleteUser")
    public SpectateurEntity deleteUser(@PathVariable Long id) {
        return spectateurService.deleteUtilateur(id);
    }
}