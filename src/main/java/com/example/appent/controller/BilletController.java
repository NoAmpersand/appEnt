package com.example.appent.controller;

import com.example.appent.dto.BilletDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.service.BilletService;
import com.example.appent.service.SpectateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billet")
public class BilletController {

    @Autowired
    private BilletService billetService;
    @Autowired
    private SpectateurService spectateurService;

    @PostMapping("/buy")
    public BilletEntity buyBillet(@RequestBody BilletDto billetDto) {
        return billetService.reserverBillet(billetDto);
    }

    @GetMapping("/{spectateurId}")
    public ResponseEntity<List<BilletEntity>> recupererTousLesBilletParSpectateur(@PathVariable Long spectateurId) {
        List<BilletEntity> billets = billetService.recupererTousLesBilletParSpectateur(spectateurId);
        return ResponseEntity.ok(billets);
    }

    @GetMapping("/{epreuveId}")
    public ResponseEntity<List<BilletEntity>> recupererTousLesBilletParEpreuve(@PathVariable Long epreuveId) {
        List<BilletEntity> billets = billetService.recupererTousLesBilletParEpreuve(epreuveId);
        return ResponseEntity.ok(billets);
    }

    @GetMapping
    public ResponseEntity<List<BilletEntity>> recupererTousLesBillets() {
        List<BilletEntity> billets = billetService.recupererTousLesBille();
        return ResponseEntity.ok(billets);
    }

}
