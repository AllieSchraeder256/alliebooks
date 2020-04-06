package com.alliebooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alliebooks.entities.ExpenseByProperty;
import com.alliebooks.entities.Property;
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
	@Transactional
	public void setPropertyRoiReports(Iterable<Property> properties, int monthsBack) {
		//TODO this isn't very good
		List<RoiReport> report = reportRepository.getPropertyRoiReport(monthsBack);
		for (Property p : properties) {
			for (RoiReport r : report) {
				if (r.getId().equals(p.getId())) {
					p.setRoiReport(r);
				}
			}
		}
		
		/*List<RoiReport> report3m = reportRepository.getPropertyRoiReport(3);
		List<RoiReport> report12m = reportRepository.getPropertyRoiReport(12);
		
		for (Property p : properties) {
			for (RoiReport r : report12m) {
				if (r.getId().equals(p.getId())) {
					p.setRoiReport12m(r);
				}
			}
			for (RoiReport r : report3m) {
				if (r.getId().equals(p.getId())) {
					p.setRoiReport3m(r);
				}
			}
		}*/
	}
}
