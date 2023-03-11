package com.rtr.service;

import com.rtr.model.Plaignant;
import com.rtr.repository.PlaignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaignantService{
    @Autowired
    private PlaignantRepository plaignantRepository;

    public Optional<Plaignant> getPlaignant(final Long id){
        return plaignantRepository.findById(id);
    }

    public Iterable<Plaignant> getPlaignants(){
        return plaignantRepository.findAll();
    }

    public void deletePlaignant(final Long id){
        plaignantRepository.deleteById(id);
    }

    public Plaignant savePlaignant(Plaignant plaignant){
        return plaignantRepository.save(plaignant);
    }
}
