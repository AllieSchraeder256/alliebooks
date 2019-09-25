package com.alliebooks.repo.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.RevenueAndLoss;

@Service
public class RevenueAndLossRepositoryImpl implements RevenueAndLossRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	
	public Iterable<RevenueAndLoss> findByYear(int year) {
		Criteria crit = em.unwrap(Session.class).createCriteria(RevenueAndLoss.class);
		crit.add(Restrictions.eq("year", year));
	    return (List<RevenueAndLoss>) crit.list();
	}

}
