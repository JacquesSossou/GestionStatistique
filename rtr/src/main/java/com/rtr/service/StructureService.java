package com.rtr.service;

import com.rtr.model.Structure;
import com.rtr.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StructureService{
    @Autowired
    private StructureRepository structureRepository;

    public Optional<Structure> getStructure(final Long id){
        return structureRepository.findById(id);
    }

    public Iterable<Structure> getStructures(){
        return structureRepository.findAll();
    }

    public void deleteStructure(final Long id){
        structureRepository.deleteById(id);
    }

    public Structure saveStructure(Structure structure){
        return structureRepository.save(structure);
    }
}
