package com.example.appent.controller;

import com.example.appent.dto.InfrastructureDto;
import com.example.appent.exception.InfrastructureExists;
import com.example.appent.service.InfrastructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InfrastructureController {

    @Autowired
    private InfrastructureService infrastructureService;

    @PostMapping("/createInfra/{email}")
    public InfrastructureDto createInfra(@PathVariable String email, @RequestBody InfrastructureDto infrastructureDto) throws InfrastructureExists {
        return this.infrastructureService.createInfrastructure(infrastructureDto, email);
    }

}
