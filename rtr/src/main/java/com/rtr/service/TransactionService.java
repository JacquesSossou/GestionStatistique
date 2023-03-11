package com.rtr.service;

import com.rtr.model.Transaction;
import com.rtr.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> getTransaction(final Long id){
        return transactionRepository.findById(id);
    }

    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(final Long id){
        transactionRepository.deleteById(id);
    }

    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}
