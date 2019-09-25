package com.alliebooks.service;

import java.util.UUID;

import com.alliebooks.entities.ExpenseType;

public interface ExpenseTypeService {
	public Iterable<ExpenseType> findAll();

	public void save(ExpenseType expenseType);

	public void delete(UUID id);

	public ExpenseType findById(UUID id);
}
