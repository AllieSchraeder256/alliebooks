package com.alliebooks.repo.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.alliebooks.entities.ExpenseByProperty;

@Service
public class ExpenseByPropertyRepositoryImpl implements ExpenseByPropertyRepositoryCustom {

	@PersistenceContext
    private EntityManager em;

	
	public Iterable<ExpenseByProperty> findByYear(int year) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ExpenseByProperty> cq = cb.createQuery(ExpenseByProperty.class);
		Root<ExpenseByProperty> root = cq.from(ExpenseByProperty.class);
		
		List<Order> orderList = new ArrayList<>();
		orderList.add(cb.asc(root.get("propertyName")));
		orderList.add(cb.asc(root.get("expenseTypeName")));
		cq.orderBy(orderList);
		
		cq.select(root).where(cb.equal(root.get("year"), year));		
	    return em.createQuery(cq).getResultList();
	}
}
