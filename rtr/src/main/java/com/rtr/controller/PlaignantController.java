package com.rtr.controller;

import com.rtr.model.Plaignant;
import com.rtr.model.Reclamation;
import com.rtr.service.PlaignantService;
import com.rtr.service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class PlaignantController {
    @Autowired
    private PlaignantService plaignantService;

    @Autowired
    private ReclamationService reclamationService;

    @PostMapping("/plaignant")
    public Plaignant createPlaignant(@RequestBody Plaignant plaignant) {
        return plaignantService.savePlaignant(plaignant);
    }



    @GetMapping("/plaignant/{id}")
    public Plaignant getPlaignant(@PathVariable("id") final Long id) {
        Optional<Plaignant> plaignant = plaignantService.getPlaignant(id);
        if(plaignant.isPresent()) {
            return plaignant.get();
        } else {
            return null;
        }
    }

    @GetMapping("/plaignants")
    public Iterable<Plaignant> getPlaignants() {
        return plaignantService.getPlaignants();
    }

    @PutMapping("/plaignant/{id}")
    public Plaignant updatePlaignant(@PathVariable("id") final Long id, @RequestBody Plaignant plaignant) {
        Optional<Plaignant> p = plaignantService.getPlaignant(id);
        if(p.isPresent()) {
            Plaignant currentPlaignant = p.get();

            String nom = plaignant.getNom();
            if(nom != null) {
                currentPlaignant.setNom(nom);
            }

            String prenoms = plaignant.getPrenoms();
            if(prenoms != null) {
                currentPlaignant.setPrenoms(prenoms);
            }

            String numero = plaignant.getNumero();
            if(numero != null) {
                currentPlaignant.setNumero(numero);
            }

            String email = plaignant.getEmail();
            if(email != null) {
                currentPlaignant.setEmail(email);
            }

           /* Set<Reclammation> reclammations = plaignant.getReclammations();
            if(reclammations != null) {
                currentPlaignant.setReclammations(reclammations);
            }*/

            plaignantService.savePlaignant(currentPlaignant);
            return currentPlaignant;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/plaignant/{id}")
    public void deletePlaignant(@PathVariable ("id") final Long id) {
        plaignantService.deletePlaignant(id);
    }
}
