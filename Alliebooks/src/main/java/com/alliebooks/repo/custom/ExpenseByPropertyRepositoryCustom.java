package com.alliebooks.repo.custom;

import com.alliebooks.entities.ExpenseByProperty;

public interface ExpenseByPropertyRepositoryCustom {

	public Iterable<ExpenseByProperty> findByYear(int year);
}
