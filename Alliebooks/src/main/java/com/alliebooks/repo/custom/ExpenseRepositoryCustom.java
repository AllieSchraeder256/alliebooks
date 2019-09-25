package com.alliebooks.repo.custom;

import java.util.Map;
import java.util.UUID;

import com.alliebooks.entities.Expense;

public interface ExpenseRepositoryCustom {

	public Iterable<Expense> findAllByDate(int month, int year);
	
	public Iterable<Expense> search(Map<String, Object> map);
}
