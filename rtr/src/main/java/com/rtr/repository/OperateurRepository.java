package com.rtr.repository;

import com.rtr.model.Operateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperateurRepository extends CrudRepository<Operateur, Long> {
}
