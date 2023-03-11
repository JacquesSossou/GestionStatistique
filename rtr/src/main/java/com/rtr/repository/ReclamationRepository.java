package com.rtr.repository;

import com.rtr.model.Reclamation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.RecursiveAction;


public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

    public Reclamation findByCodeConsultation(String code);

    public Reclamation findByReferenceDossier(String refDossier);
}
