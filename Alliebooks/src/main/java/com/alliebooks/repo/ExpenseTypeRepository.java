package com.alliebooks.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.ExpenseType;

@RepositoryRestResource(collectionResourceRel = "expense_types", path = "expense_types")
public interface ExpenseTypeRepository extends CrudRepository<ExpenseType, UUID>{
	
	Iterable<ExpenseType> findByActiveTrueOrderByName();
	
}