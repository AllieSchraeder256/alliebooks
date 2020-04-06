package com.alliebooks.service;

import com.alliebooks.entities.ExpenseByProperty;
import com.alliebooks.entities.Property;
import com.alliebooks.entities.RevenueAndLoss;

public interface ReportService {
	public Iterable<RevenueAndLoss> buildRevenueAndLossByProperty(int year);
	
	public Iterable<ExpenseByProperty> buildExpenseByProperty(int year);

	public void setPropertyRoiReports(Iterable<Property> properties, int monthsBack);
}
