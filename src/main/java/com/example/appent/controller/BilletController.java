package com.example.appent.controller;

import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.repository.BilletRepository;
import com.example.appent.repository.ControlleurRepository;
import com.example.appent.repository.SpectateurRepository;
import com.example.appent.service.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BilletController {
    @Autowired
    BilletService billetService;
    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private BilletRepository BilletRepository;

    @GetMapping("/getBillets/{mail}")
    public List<BilletEntity> recupererTousLesBilletsUtil(@PathVariable String mail){
        SpectateurEntity spec = spectateurRepository.findByEmail((mail));
        return BilletRepository.findAllBySpectateur(spec);
    }
    @PostMapping("/validerBillet")
    public ResponseEntity<?> validerBillet(@RequestParam String mail, @RequestParam Long idBillet) {
        billetService.validerBillet(mail, idBillet);
        return ResponseEntity.ok("Billet ok");

    }
}
