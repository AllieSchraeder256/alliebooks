package com.alliebooks.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.ExpenseType;
import com.alliebooks.repo.ExpenseTypeRepository;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
	
	@Autowired
	private ExpenseTypeRepository expenseTypeRepo;

	public Iterable<ExpenseType> findAll() {
		return expenseTypeRepo.findAll();
	}

	public void save(ExpenseType expenseType) {
		expenseTypeRepo.save(expenseType);
	}

	public void delete(UUID id) {
		expenseTypeRepo.deleteById(id);
	}

	public ExpenseType findById(UUID id) {
		return expenseTypeRepo.findById(id).get();
	}

}
