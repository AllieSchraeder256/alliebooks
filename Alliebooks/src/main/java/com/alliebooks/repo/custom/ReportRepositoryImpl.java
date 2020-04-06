package com.alliebooks.repo.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alliebooks.entities.RoiReport;

@Transactional
@Repository
public class ReportRepositoryImpl implements ReportRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
	
	
	public List<RoiReport> getPropertyRoiReport(int monthsBack) {
		Query query = em.createNativeQuery(
				"select f.id, f.year_income/ "+monthsBack+"  as operating_income, " +
			    "  f.year_expenses/ "+monthsBack+" as operating_expenses, f.expenses, " +
			    "  ROUND((((f.year_income - f.year_expenses) / f.expenses)* 100),2) as roi, " + 
			    "  (f.year_income - f.year_expenses)/ "+monthsBack+" as noi from " + 
				"  (select p.id, p.name, sum(r.amount) as year_income, " + 
				"  (select sum(amount) from expenses e where e.property_id = p.id " +
				"    and e.date >= " +minusMonths(monthsBack)+ ") as year_expenses, " + 
				"  (select sum(amount) from expenses e where e.property_id = p.id) as expenses " + 
				"  from rent_payments r " + 
				"  join units u on r.unit_id = u.id " + 
				"  join properties p on u.property_id = p.id " + 
				"  where r.date >= " + minusMonths(monthsBack)+
				"  group by p.id, p.name) f", RoiReport.class);
		List<RoiReport> list = query.getResultList();
		return list;
	}
	
	private String minusMonths(int monthsBack) {
		DateTime date = DateTime.now().minusMonths(monthsBack);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return String.format("'%s'\\:\\:date", formatter.print(date));
	}
}
