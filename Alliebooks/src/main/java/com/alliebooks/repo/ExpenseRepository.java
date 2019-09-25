package com.alliebooks.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.alliebooks.entities.Expense;
import com.alliebooks.repo.custom.ExpenseRepositoryCustom;

public interface ExpenseRepository extends CrudRepository<Expense, UUID>, ExpenseRepositoryCustom{
	
	
}