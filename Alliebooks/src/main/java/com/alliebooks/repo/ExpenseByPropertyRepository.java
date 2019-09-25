package com.alliebooks.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.alliebooks.entities.ExpenseByProperty;
import com.alliebooks.repo.custom.ExpenseByPropertyRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "v_expenses_by_property", path = "v_expenses_by_property")
public interface ExpenseByPropertyRepository extends CrudRepository<ExpenseByProperty, String>, ExpenseByPropertyRepositoryCustom {
	
}