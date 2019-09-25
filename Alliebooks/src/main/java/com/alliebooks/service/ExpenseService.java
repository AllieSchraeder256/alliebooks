package com.alliebooks.service;

import java.util.UUID;

import com.alliebooks.entities.Expense;

public interface ExpenseService {
	
	public Iterable<Expense> findAllByDate(int month, int year);
	
	public Iterable<Expense> search(String query);
	
	public Expense getExpenseById(UUID id);
	
	public void saveExpense(Expense expense);
	
	public void deleteExpense(UUID id);
}
