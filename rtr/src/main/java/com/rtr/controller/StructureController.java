package com.rtr.controller;

import com.rtr.model.Prestation;
import com.rtr.model.Reclamation;
import com.rtr.model.Structure;
import com.rtr.model.Transaction;
import com.rtr.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = {"/api/", "/api/auth"})
public class StructureController {
    @Autowired
    private StructureService structureService;

    @PostMapping("/structure")
    public Structure createStructure(@RequestBody Structure structure) {
        return structureService.saveStructure(structure);
    }

    @GetMapping("/structure/{id}")
    public Structure getStructure(@PathVariable("id") final Long id) {
        Optional<Structure> structure = structureService.getStructure(id);
        if(structure.isPresent()) {
            return structure.get();
        } else {
            return null;
        }
    }


    @GetMapping("/structures")
    public Iterable<Structure> getStructures() {
        return structureService.getStructures();
    }

    @PutMapping("/structure/{id}")
    public Structure updateStructure(@PathVariable("id") final Long id, @RequestBody Structure structure) {
        Optional<Structure> p = structureService.getStructure(id);
        if(p.isPresent()) {
            Structure currentStructure = p.get();

            String nom = structure.getNom();
            if(nom != null) {
                currentStructure.setNom(nom);
            }

            String description = structure.getDescription();
            if(description != null) {
                currentStructure.setDescription(description);
            }

            String sigle = structure.getSigle();
            if(sigle != null) {
                currentStructure.setSigle(sigle);
            }

            Set<Prestation> prestations = structure.getPrestations();
            if(prestations != null) {
                currentStructure.setPrestations(prestations);
            }

            Set<Reclamation> reclamations = structure.getReclamations();
            if(reclamations != null) {
                currentStructure.setReclamations(reclamations);
            }

            structureService.saveStructure(currentStructure);
            return currentStructure;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/structure/{id}")
    public void deleteStructure(@PathVariable ("id") final Long id) {
        structureService.deleteStructure(id);
    }
}
