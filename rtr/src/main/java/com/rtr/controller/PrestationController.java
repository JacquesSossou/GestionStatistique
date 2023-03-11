package com.rtr.controller;


import com.rtr.model.Prestation;
import com.rtr.model.Reclamation;
import com.rtr.model.Structure;
import com.rtr.service.PrestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = {"/api/", "/api/auth"})
public class PrestationController {
    @Autowired
    private PrestationService prestationService;

    @PostMapping("/prestation")
    public Prestation createPrestation(@RequestBody Prestation prestation) {
        return prestationService.savePrestation(prestation);
    }

 /*   @GetMapping("/exportPrestation")
    public void exportExamples(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Iterable<Prestation> prestations = prestationService.getPrestations();

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"examples.csv\"");

        StatefulBeanToCsv<Prestation> writer = new StatefulBeanToCsvBuilder<Prestation>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        writer.write((Prestation) prestations);
    }*/


    @GetMapping("/prestation/{id}")
    public Prestation getPrestation(@PathVariable("id") final Long id) {
        Optional<Prestation> prestation = prestationService.getPrestation(id);
        if(prestation.isPresent()) {
            return prestation.get();
        } else {
            return null;
        }
    }

    @GetMapping("/prestations")
    public Iterable<Prestation> getPrestations() {
        return prestationService.getPrestations();
    }

    @PutMapping("/prestation/{id}")
    public Prestation updatePrestation(@PathVariable("id") final Long id, @RequestBody Prestation prestation) {
        Optional<Prestation> p = prestationService.getPrestation(id);
        if(p.isPresent()) {
            Prestation currentPrestation = p.get();

            String libelle = prestation.getLibelle();
            if(libelle != null) {
                currentPrestation.setLibelle(libelle);
            }

            double montant = prestation.getMontant();
            if(montant > 0) {
                currentPrestation.setMontant(montant);
            }



            Structure structure = prestation.getStructure();
            if(structure != null) {
                currentPrestation.setStructure(structure);
            }

            Set<Reclamation> reclamations = prestation.getReclamations();
            if(reclamations != null) {
                currentPrestation.setReclamations(reclamations);
            }

            prestationService.savePrestation(currentPrestation);
            return currentPrestation;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/prestation/{id}")
    public void deletePrestation(@PathVariable ("id") final Long id) {
        prestationService.deletePrestation(id);
    }
}
