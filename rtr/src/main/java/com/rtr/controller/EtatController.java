package com.rtr.controller;

import com.rtr.model.Etat;
import com.rtr.model.ReclamationEtat;
import com.rtr.service.EtatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RequestMapping("/api/")
@RestController
public class EtatController {
    @Autowired
    private EtatService etatService;

    @PostMapping("/etat")
    public Etat createEtat(@RequestBody Etat etat) {
        return etatService.saveEtat(etat);
    }

    @GetMapping("/etat/{id}")
    public Etat getEtat(@PathVariable("id") final Long id) {
        Optional<Etat> etat = etatService.getEtat(id);
        if(etat.isPresent()) {
            return etat.get();
        } else {
            return null;
        }
    }

    @GetMapping("/etatlabel/{label}")
    public Etat getEtatByLabel(@PathVariable("label") final String label) {
        Optional<Etat> etat = etatService.getEtatByLabel(label);
        if(etat.isPresent()) {
            return etat.get();
        } else {
            return null;
        }
    }


    @GetMapping("/etats")
    public Iterable<Etat> getEtats() {
        return etatService.getEtats();
    }

    @PutMapping("/etat/{id}")
    public Etat updateEtat(@PathVariable("id") final Long id, @RequestBody Etat etat) {
        Optional<Etat> r = etatService.getEtat(id);
        if(r.isPresent()) {
            Etat currentEtat = r.get();

            String label = etat.getLabel();
            if(label != null) {
                currentEtat.setLabel(label);
            }

            Set<ReclamationEtat> reclamationEtats = etat.getReclamationEtats();
            if(reclamationEtats != null) {
                currentEtat.setReclamationEtats(reclamationEtats);
            }


            etatService.saveEtat(currentEtat);
            return currentEtat;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/etat/{id}")
    public void deleteEtat(@PathVariable ("id") final Long id) {
        etatService.deleteEtat(id);
    }
}
