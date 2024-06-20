package com.example.appent.controller;

import com.example.appent.dto.ResultatDto;
import com.example.appent.dto.DelegationDto;
import com.example.appent.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @PostMapping("/{email}/publierResultat")
    public void publierResultats(@PathVariable String email, @RequestBody List<ResultatDto> resultatsDTO) {
        resultatService.publierResultats(email, resultatsDTO);
    }

    @GetMapping("/classement")
    public List<DelegationDto> getClassementGeneral() {
        return resultatService.getClassementGeneral();
    }
}
