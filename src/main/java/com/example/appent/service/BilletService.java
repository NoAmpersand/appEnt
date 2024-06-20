package com.example.appent.service;

import com.example.appent.dto.BilletDto;
import com.example.appent.entity.BilletEntity;
import com.example.appent.entity.EpreuveEntity;
import com.example.appent.entity.SpectateurEntity;
import com.example.appent.exception.BilletBuyNotPossible;
import com.example.appent.exception.BilletInexistant;
import com.example.appent.exception.SpectateurInexistant;
import com.example.appent.helpers.BilletState;
import com.example.appent.repository.BilletRepository;
import com.example.appent.repository.EpreuveRepository;
import com.example.appent.repository.SpectateurRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BilletService {
    private BilletRepository billetRepository;
    private SpectateurRepository spectateurRepository;
    private EpreuveRepository epreuveRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BilletService(BilletRepository billetRepository,EpreuveRepository epreuveRepository , SpectateurRepository spectateurRepository) {
        this.billetRepository = billetRepository;
        this.spectateurRepository = spectateurRepository;
        this.epreuveRepository = epreuveRepository;
    }

    public BilletState getBilletState(long id) throws BilletInexistant {
        BilletEntity b = this.billetRepository.findByIdBillet(id);
        if (b == null) {
            throw new BilletInexistant(HttpStatus.NOT_FOUND);
        }
        return b.getEtat();
    }

    public BilletEntity reserverBillet(@NotNull BilletDto billetDTO){
        SpectateurEntity spectateur = spectateurRepository.findById(billetDTO.getSId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid spectateur ID"));
        EpreuveEntity epreuve = epreuveRepository.findById(billetDTO.getEpId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid epreuve ID"));


        BilletEntity billet = new BilletEntity();
        billet.setSpectateur(spectateur);
        billet.setEpreuve(epreuve);
        billet.setEtat(billetDTO.getState());
        billet.setPrix(billetDTO.getPrix());


        epreuve.setNbPlacesSpectateur(epreuve.getNbPlacesSpectateur() - 1);
        return billetRepository.save(billet);
    }


    public int billetParSpectateur(SpectateurEntity spectateur, EpreuveEntity epreuve) {
        int i = 0;

        if(epreuve==null || spectateur==null){
            throw new IllegalArgumentException("Indiquez des épreuves et des spectateurs valide ");
        }

        for (BilletEntity b : spectateur.getBillets()) {
            if(b.getEtat()==BilletState.CREATED && epreuve== b.getEpreuve()){
                i++;
            }
        }
        return i;
    }


    public String annulerBillet(Long idBillet, Long idS) throws BilletInexistant, SpectateurInexistant {
        if(this.billetRepository.findByIdBilletAndSpectateurId(idBillet, idS) == null){
            throw new BilletInexistant(HttpStatus.NOT_FOUND);
        }
        BilletEntity b = this.billetRepository.findByIdBillet(idBillet);
        EpreuveEntity e = b.getEpreuve();

        if(this.spectateurRepository.findById(idS).isEmpty()){
            throw new SpectateurInexistant(HttpStatus.NOT_FOUND);
        }

        Timestamp dateEpreuve = e.getDate();
        Timestamp today = new Timestamp(dateEpreuve.getTime());

        // Conversion des Timestamp en LocalDateTime
        LocalDateTime localDateEpreuve = dateEpreuve.toLocalDateTime();
        LocalDateTime localDateToday = today.toLocalDateTime();

        // Calcul de la différence en jours
        long daysBetween = ChronoUnit.DAYS.between(localDateToday, localDateEpreuve);

        if(daysBetween<3){
            throw new RuntimeException("Annulation impossible dans les 3 jours avant l'épreuve.");
        }

        b.setEtat(BilletState.CANCELLED);
        this.billetRepository.save(b);

        float remboursement = 0;
        if(daysBetween>7){
            remboursement= b.getPrix();
        } else {
            remboursement = (b.getPrix())/2;
        }

        return "Le billet a bien été annulé. Vous avez été rembourser de : " + remboursement;

    }

    public List<BilletEntity> recupererTousLesBilletParSpectateur(Long spectateurId){
        return billetRepository.findBySpectateurId(spectateurId);
    }

    public List<BilletEntity> recupererTousLesBilletParEpreuve(Long spectateurId){
        return billetRepository.findByEpreuveId(spectateurId);
    }

    public List<BilletEntity> recupererTousLesBille(){
        return billetRepository.findAll();
    }

}
