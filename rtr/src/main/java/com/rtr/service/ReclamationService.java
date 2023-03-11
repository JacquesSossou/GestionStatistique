package com.rtr.service;

import com.rtr.model.Reclamation;
import com.rtr.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReclamationService{
    @Autowired
    private ReclamationRepository reclamationRepository;

    public Optional<Reclamation> getReclamation(final Long id){
        return reclamationRepository.findById(id);
    }

    public Iterable<Reclamation> getReclamations(){
        return reclamationRepository.findAll();
    }


    public Reclamation findByCode(String code){
        return  reclamationRepository.findByCodeConsultation(code);
    }

    public Reclamation findByDossier(String reference){
        return  reclamationRepository.findByReferenceDossier(reference);
    }
    public void deleteReclamation(final Long id){
        reclamationRepository.deleteById(id);
    }

    public Reclamation saveReclamation(Reclamation reclamation){
        return reclamationRepository.save(reclamation);
    }
}
