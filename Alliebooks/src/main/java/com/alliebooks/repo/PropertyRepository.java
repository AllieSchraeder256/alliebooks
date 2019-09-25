package com.alliebooks.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.Property;

@RepositoryRestResource(collectionResourceRel = "properties", path = "properties")
public interface PropertyRepository extends CrudRepository<Property, UUID>{
	
}