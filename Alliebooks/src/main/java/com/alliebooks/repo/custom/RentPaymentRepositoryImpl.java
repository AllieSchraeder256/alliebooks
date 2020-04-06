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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.RentPayment;

@Service
public class RentPaymentRepositoryImpl implements RentPaymentRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	
	public Iterable<RentPayment> findAllByDate(int month, int year) {
		Criteria crit = em.unwrap(Session.class).createCriteria(RentPayment.class);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = fmt.parse(String.format("%s-%s-01", year, month));
			if (++month == 13) {
				month = 1;
				year++;
			}
			d2 = fmt.parse(String.format("%s-%s-01", year, month));
			
			crit.add(Restrictions.ge("date", d1))
				.add(Restrictions.lt("date", d2));
	        return (List<RentPayment>) crit.list();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ArrayList<RentPayment>();
	}


	public Iterable<RentPayment> search(Map<String, Object> map) {
		//TODO rewrite with jpa specifications
		Criteria crit = em.unwrap(Session.class).createCriteria(RentPayment.class);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			int year = map.get("year") == null ? 0: (Integer) map.get("year");
			if (year > 0) {
				d1 = fmt.parse(String.format("%s-01-01", year));
				d2 = fmt.parse(String.format("%s-01-01", year + 1));
				crit.add(Restrictions.ge("date", d1))
				.add(Restrictions.lt("date", d2));
			}
			if (map.get("tenant") != null) {
				crit.add(Restrictions.ilike("tenant", map.get("tenant")));
			}
			if (map.get("note") != null) {
				crit.add(Restrictions.ilike("note", map.get("note")));
			}
	        return (List<RentPayment>) crit.list();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return new ArrayList<RentPayment>();
	}


	@Override
	public Long countPaymentsByUnitAndYear(UUID unitId, int year) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = fmt.parse(String.format("%s-01-01", year));
			d2 = fmt.parse(String.format("%s-01-01", year + 1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<RentPayment> root = cq.from(RentPayment.class);
		cq.select(cb.count(root));
		cq.where(cb.equal(root.get("unit_id"), unitId));
		Path<Date> datePath = root.get("date");
		cq.where(cb.between(datePath, d1, d2));//TODO idk why I need to get as a path before comparing, look into that
		return em.createQuery(cq).getSingleResult();
	}
}
