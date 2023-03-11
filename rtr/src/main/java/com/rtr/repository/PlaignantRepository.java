package com.rtr.repository;

import com.rtr.model.Plaignant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaignantRepository extends CrudRepository<Plaignant, Long> {
}
