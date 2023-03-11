package com.rtr.controller;

import com.rtr.model.Etat;
import com.rtr.model.Reclamation;
import com.rtr.model.ReclamationEtat;
import com.rtr.model.ReclamationEtatKey;
import com.rtr.service.ReclamationEtatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/api/", "/api/auth"})
public class ReclamationEtatController {
    @Autowired
    private ReclamationEtatService reclamationEtatService;

    @PostMapping("/reclamationEtat")
    public ReclamationEtat createReclamationEtat(@RequestBody ReclamationEtat reclamationEtat) {
        return reclamationEtatService.saveReclamationEtat(reclamationEtat);
    }

   /* @GetMapping("/reclamationEtat/{id}")
    public ReclamationEtat getReclamationEtat(@PathVariable("id") final ReclamationEtatKey id) {
        Optional<ReclamationEtat> reclamationEtat = reclamationEtatService.getReclamationEtat(id);
        if(reclamationEtat.isPresent()) {
            return reclamationEtat.get();
        } else {
            return null;
        }
    }*/

    @GetMapping("/reclamationEtat/{id}")
    public Iterable<ReclamationEtat> getReclamationEtatByReclamationId(@PathVariable("id") final Long id) {
    return reclamationEtatService.getReclamationEtatByReclamationId(id);

    }

    @GetMapping("/reclamationEtats")
    public Iterable<ReclamationEtat> getReclamationEtats() {
        return reclamationEtatService.getReclamationEtats();
    }

    @PutMapping("/reclamationEtat/{id}")
    public ReclamationEtat updateReclamationEtat(@PathVariable("id") final ReclamationEtatKey id, @RequestBody ReclamationEtat reclamationEtat) {
        Optional<ReclamationEtat> p = reclamationEtatService.getReclamationEtat(id);
        if(p.isPresent()) {
            ReclamationEtat currentReclamationEtat = p.get();

            Etat etat = reclamationEtat.getEtat();
            if(etat != null) {
                currentReclamationEtat.setEtat(etat);
            }

            Reclamation reclamation = reclamationEtat.getReclamation();
            if(etat != null) {
                currentReclamationEtat.setReclamation(reclamation);
            }

            LocalDateTime dateTime = reclamationEtat.getDatetime();
            if(dateTime != null) {
                currentReclamationEtat.setDatetime(dateTime);
            }

            reclamationEtatService.saveReclamationEtat(currentReclamationEtat);
            return currentReclamationEtat;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/reclamationEtat/{id}")
    public void deleteReclamationEtat(@PathVariable ("id") final ReclamationEtatKey id) {
        reclamationEtatService.deleteReclamationEtat(id);
    }
}
