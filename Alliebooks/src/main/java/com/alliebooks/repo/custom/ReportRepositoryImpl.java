package com.alliebooks.repo.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.alliebooks.entities.RoiReport;

@Service
public class ReportRepositoryImpl implements ReportRepositoryCustom {

	@PersistenceContext
    private EntityManager em;
	
	public List<RoiReport> getPropertyRoiReport() {
		Query query = em.createNativeQuery(
				"select f.id, f.income, f.expenses, ROUND((((f.income - f.expenses) / f.expenses)* 100),2) as roi from " + 
				"  (select p.id, p.name, sum(r.amount) as income, " + 
				"    (select sum(amount) from expenses e where e.property_id = p.id) as expenses " + 
				"  from rent_payments r " + 
				"  join units u on r.unit_id = u.id " + 
				"  join properties p on u.property_id = p.id " + 
				"  group by p.id, p.name) f", RoiReport.class);
		
		List<RoiReport> list = query.getResultList();
		return list;
	}
}
