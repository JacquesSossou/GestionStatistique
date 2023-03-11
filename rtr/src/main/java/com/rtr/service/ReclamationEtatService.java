package com.rtr.service;

import com.rtr.model.Etat;
import com.rtr.model.Reclamation;
import com.rtr.model.ReclamationEtat;
import com.rtr.model.ReclamationEtatKey;
import com.rtr.repository.ReclamationEtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReclamationEtatService{
    @Autowired
    private ReclamationEtatRepository reclamationEtatRepository;

    public Optional<ReclamationEtat> getReclamationEtat(final ReclamationEtatKey id){
        return reclamationEtatRepository.findById(id);
    }

    public Iterable<ReclamationEtat> getReclamationEtats(){
        return reclamationEtatRepository.findAll();
    }

    public void deleteReclamationEtat(final ReclamationEtatKey id){
        reclamationEtatRepository.deleteById(id);
    }

    public ReclamationEtat saveReclamationEtat(ReclamationEtat reclamationEtat){
        return reclamationEtatRepository.save(reclamationEtat);
    }

    public  Iterable<ReclamationEtat> getReclamationEtatByReclamationId(final Long id){
        return reclamationEtatRepository.findByReclamationId(id);
    }
}
