package com.alliebooks.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.Unit;

@RepositoryRestResource(collectionResourceRel = "units", path = "units")
public interface UnitRepository extends CrudRepository<Unit, UUID>{
	
}