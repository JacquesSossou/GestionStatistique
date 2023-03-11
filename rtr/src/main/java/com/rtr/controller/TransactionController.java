package com.rtr.controller;

import com.rtr.model.*;
import com.rtr.model.Transaction;
import com.rtr.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = {"/api/","/api/auth"})
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable("id") final Long id) {
        Optional<Transaction> transaction = transactionService.getTransaction(id);
        if(transaction.isPresent()) {
            return transaction.get();
        } else {
            return null;
        }
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @PutMapping("/transaction/{id}")
    public Transaction updateTransaction(@PathVariable("id") final Long id, @RequestBody Transaction transaction) {
        Optional<Transaction> t = transactionService.getTransaction(id);
        if(t.isPresent()) {
            Transaction currentTransaction = t.get();

            String numero = transaction.getNumero();
            if(numero != null) {
                currentTransaction.setNumero(numero);
            }

            String reference = transaction.getReference();
            if(reference != null) {
                currentTransaction.setReference(reference);
            }

            Operateur operateur = transaction.getOperateur();
            if(operateur != null) {
                currentTransaction.setOperateur(operateur);
            }
            Reclamation reclamation = transaction.getReclamation();
            if(reclamation != null) {
                currentTransaction.setReclamation(reclamation);
            }
            transactionService.saveTransaction(currentTransaction);
            return currentTransaction;
        } else {
            return null;
        }
    }

    @DeleteMapping ("/transaction/{id}")
    public void deleteTransaction(@PathVariable ("id") final Long id) {
        transactionService.deleteTransaction(id);
    }
}
