package com.rtr.service;

import com.rtr.model.Etat;
import com.rtr.repository.EtatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EtatService{
    @Autowired
    private EtatRepository etatRepository;

    public Optional<Etat> getEtat(final Long id){
        return etatRepository.findById(id);
    }
    public Optional<Etat> getEtatByLabel(final String label){
        return etatRepository.findByLabel(label);
    }

    public Iterable<Etat> getEtats(){
        return etatRepository.findAll();
    }

    public void deleteEtat(final Long id){
        etatRepository.deleteById(id);
    }

    public Etat saveEtat(Etat etat){
        return etatRepository.save(etat);
    }
}
