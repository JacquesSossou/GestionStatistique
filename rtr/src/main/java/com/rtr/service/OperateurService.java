package com.rtr.service;

import com.rtr.model.Operateur;
import com.rtr.repository.OperateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperateurService{
    @Autowired
    private OperateurRepository operateurRepository;

    public Optional<Operateur> getOperateur(final Long id){
        return operateurRepository.findById(id);
    }

    public Iterable<Operateur> getOperateurs(){
        return operateurRepository.findAll();
    }

    public void deleteOperateur(final Long id){
        operateurRepository.deleteById(id);
    }

    public Operateur saveOperateur(Operateur operateur){
        return operateurRepository.save(operateur);
    }
}
