package com.example.appent.service;

import com.example.appent.dto.OrganisateurDTO;
import com.example.appent.entity.OrganisateurEntity;
import com.example.appent.exception.OrganisateurAlreadyExistsException;
import com.example.appent.repository.OrganisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class OrganisateurService {

    private final OrganisateurRepository organisateurRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public OrganisateurService(OrganisateurRepository organisateurRepository) {
        this.organisateurRepository = organisateurRepository;
    }

    public OrganisateurDTO createOrganisateur(OrganisateurDTO organisateurDTO) throws OrganisateurAlreadyExistsException {
        if (organisateurRepository.findByEmail(organisateurDTO.getEmail()) == null) {
            OrganisateurEntity organisateurEntity = modelMapper.map(organisateurDTO, OrganisateurEntity.class);
            organisateurRepository.save(organisateurEntity);
            return modelMapper.map(organisateurEntity, OrganisateurDTO.class);
        } else {
            throw new OrganisateurAlreadyExistsException("Organisateur already exists");
        }
    }
}