package com.rtr.service;

import com.rtr.model.Prestation;
import com.rtr.repository.PrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrestationService{
    @Autowired
    private PrestationRepository prestationRepository;

    public Optional<Prestation> getPrestation(final Long id){
        return prestationRepository.findById(id);
    }

    public Iterable<Prestation> getPrestations(){
        return prestationRepository.findAll();
    }

    public void deletePrestation(final Long id){
        prestationRepository.deleteById(id);
    }

    public Prestation savePrestation(Prestation prestation){
        return prestationRepository.save(prestation);
    }
}
