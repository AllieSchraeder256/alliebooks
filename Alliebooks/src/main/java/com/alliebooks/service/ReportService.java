package com.alliebooks.service;

import java.util.List;

import com.alliebooks.entities.ExpenseByProperty;
import com.alliebooks.entities.RevenueAndLoss;
import com.alliebooks.entities.RoiReport;

public interface ReportService {
	public Iterable<RevenueAndLoss> buildRevenueAndLossByProperty(int year);
	
	public Iterable<ExpenseByProperty> buildExpenseByProperty(int year);

	public List<RoiReport> getPropertyRoiReport();
}
