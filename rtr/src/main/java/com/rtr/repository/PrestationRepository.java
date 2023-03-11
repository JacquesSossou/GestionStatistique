package com.rtr.repository;

import com.rtr.model.Prestation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationRepository extends CrudRepository<Prestation, Long> {
}
