package com.alliebooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.ExpenseByProperty;
import com.alliebooks.entities.RevenueAndLoss;
import com.alliebooks.entities.RoiReport;
import com.alliebooks.repo.ExpenseByPropertyRepository;
import com.alliebooks.repo.RevenueAndLossRepository;
import com.alliebooks.repo.custom.ReportRepositoryCustom;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ExpenseByPropertyRepository expenseByPropRepo;
	
	@Autowired
	private RevenueAndLossRepository revenueAndLossRepo;
	
	@Autowired
	private ReportRepositoryCustom reportRepository;

	@Override
	public Iterable<RevenueAndLoss> buildRevenueAndLossByProperty(int year) {
		return revenueAndLossRepo.findByYear(year);
	}

	@Override
	public Iterable<ExpenseByProperty> buildExpenseByProperty(int year) {
		Iterable<ExpenseByProperty> report = expenseByPropRepo.findByYear(year);
		return report;
	}
	
	@Override
	public List<RoiReport> getPropertyRoiReport() {
		return reportRepository.getPropertyRoiReport();
	}
}
