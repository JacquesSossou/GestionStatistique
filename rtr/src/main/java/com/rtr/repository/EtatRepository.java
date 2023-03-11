package com.rtr.repository;

import com.rtr.model.Etat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtatRepository extends CrudRepository<Etat, Long> {
    public Optional<Etat> findByLabel(String label);
}
