package com.rtr.controller;

import com.rtr.model.Operateur;
import com.rtr.model.Transaction;
import com.rtr.service.OperateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = {"/api/", "/api/auth"})
public class OperateurController {
    @Autowired
    private OperateurService operateurService;

    @PostMapping("/operateur")
    public Operateur createOperateur(@RequestBody Operateur operateur) {
        return operateurService.saveOperateur(operateur);
    }

    @GetMapping("/operateur/{id}")
    public Operateur getOperateur(@PathVariable("id") final Long id) {
        Optional<Operateur> operateur = operateurService.getOperateur(id);
        if(operateur.isPresent()) {
            return operateur.get();
        } else {
            return null;
        }
    }

    @GetMapping("/operateurs")
    public Iterable<Operateur> getOperateurs() {
        return operateurService.getOperateurs();
    }

    @PutMapping("/operateur/{id}")
    public Operateur updateOperateur(@PathVariable("id") final Long id, @RequestBody Operateur operateur) {
        Optional<Operateur> o = operateurService.getOperateur(id);
        if(o.isPresent()) {
            Operateur currentOperateur = o.get();

            String name = operateur.getNom();
            if(name != null) {
                currentOperateur.setNom(name);
            }

            Set<Transaction> transactions = operateur.getTransactions();
            if(transactions != null) {
                currentOperateur.setTransactions(transactions);
            }

            operateurService.saveOperateur(currentOperateur);
            return currentOperateur;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/operateur/{id}")
    public void deleteOperateur(@PathVariable ("id") final Long id) {
        operateurService.deleteOperateur(id);
    }
}
