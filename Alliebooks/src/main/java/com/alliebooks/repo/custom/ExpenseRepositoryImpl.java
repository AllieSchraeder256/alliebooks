package com.alliebooks.repo.custom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.Expense;

@Service
public class ExpenseRepositoryImpl implements ExpenseRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	
	public Iterable<Expense> findAllByDate(int month, int year) {
		Criteria crit = em.unwrap(Session.class).createCriteria(Expense.class);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = fmt.parse(String.format("%s-%s-01", year, month));
			if (++month == 13) {
				month = 1;
			}
			d2 = fmt.parse(String.format("%s-%s-01", year, month));
			
			crit.add(Restrictions.ge("date", d1))
				.add(Restrictions.lt("date", d2));
	        return (List<Expense>) crit.list();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ArrayList<Expense>();
	}


	public Iterable<Expense> search(Map<String, Object> map) {
		Criteria crit = em.unwrap(Session.class).createCriteria(Expense.class);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			int year = map.get("year") == null? 0 : (Integer) map.get("year");
			if (year > 0) {
				d1 = fmt.parse(String.format("%s-01-01", year));
				d2 = fmt.parse(String.format("%s-01-01", year + 1));
				crit.add(Restrictions.ge("date", d1))
				.add(Restrictions.lt("date", d2));
			}
			if (map.get("store") != null) {
				crit.add(Restrictions.ilike("store", map.get("store")));
			}
			if (map.get("comment") != null) {
				crit.add(Restrictions.ilike("comment", map.get("comment")));
			}
	        return (List<Expense>) crit.list();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ArrayList<Expense>();
	}
}
