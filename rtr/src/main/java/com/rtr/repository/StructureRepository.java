package com.rtr.repository;

import com.rtr.model.Structure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepository  extends CrudRepository<Structure, Long> {
}
