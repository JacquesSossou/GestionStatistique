package com.rtr.repository;

import com.rtr.model.Reclamation;
import com.rtr.model.ReclamationEtat;
import com.rtr.model.ReclamationEtatKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationEtatRepository extends CrudRepository<ReclamationEtat, ReclamationEtatKey> {
    public Iterable<ReclamationEtat> findByReclamationId(final Long id);

}
