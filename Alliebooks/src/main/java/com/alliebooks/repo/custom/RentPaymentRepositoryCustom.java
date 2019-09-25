package com.alliebooks.repo.custom;

import java.util.Map;
import java.util.UUID;

import com.alliebooks.entities.RentPayment;

public interface RentPaymentRepositoryCustom {

	public Iterable<RentPayment> findAllByDate(int month, int year);
	
	public Iterable<RentPayment> search(Map<String, Object> map);
	
	public Long countPaymentsByUnitAndYear(UUID propertyId, int year);
}
