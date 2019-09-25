package com.alliebooks.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alliebooks.Utils;
import com.alliebooks.entities.Expense;
import com.alliebooks.repo.ExpenseRepository;

@Component
public class ExpenseServiceImpl implements ExpenseService {

	private static final String[] SEARCH_FIELDS = new String[] {"store", "comment", "year"};//TODO add type
	
	@Autowired
	private ExpenseRepository repository;
	
	@Autowired
	private ImageService imageService;
	
	public Iterable<Expense> findAllByDate(int month, int year) {
		Iterable<Expense> list = repository.findAllByDate(month, year);
		return list;
	}

	public void saveExpense(Expense expense) {
		imageService.saveImage(expense);
		repository.save(expense);
	}
	
	public Expense getExpenseById(UUID id) {
		return repository.findById(id).get();
	}

	public void deleteExpense(UUID id) {
		repository.deleteById(id);
	}

	public Iterable<Expense> search(String query) {
		return repository.search(Utils.getSearchCriteria(query, SEARCH_FIELDS));
	}
}
