package com.example.appent.service;

import com.example.appent.dto.InfrastructureDto;
import com.example.appent.entity.InfrastructureEntity;
import com.example.appent.exception.InfrastructureExists;
import com.example.appent.exception.OrganisateurNotExisting;
import com.example.appent.repository.InfrastructureSportiveRepository;
import com.example.appent.repository.OrganisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class InfrastructureService {
    private InfrastructureSportiveRepository sportiveRepository;
    private OrganisateurRepository organisateurRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public InfrastructureService(InfrastructureSportiveRepository sportiveRepository, OrganisateurRepository organisateurRepository) {
        this.sportiveRepository = sportiveRepository;
        this.organisateurRepository = organisateurRepository;
    }

    public InfrastructureDto createInfrastructure(InfrastructureDto infrastructureDto,String email) throws InfrastructureExists, OrganisateurNotExisting {
        if(this.sportiveRepository.findByNom(infrastructureDto.getNom()).isPresent()){
            throw new InfrastructureExists();
        }
        if(this.organisateurRepository.findByEmail(email) == null){
            throw new OrganisateurNotExisting();
        }

        InfrastructureEntity infrastructureEntity = new InfrastructureEntity(
                infrastructureDto.getNom(),
                infrastructureDto.getCapacite(),
                infrastructureDto.getAdresse()
        );
        this.sportiveRepository.save(infrastructureEntity);
        return this.modelMapper.map(infrastructureEntity, InfrastructureDto.class);
    }
}
