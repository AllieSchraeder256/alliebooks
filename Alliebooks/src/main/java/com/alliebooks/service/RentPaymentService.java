package com.alliebooks.service;

import java.util.UUID;

import com.alliebooks.entities.RentPayment;

public interface RentPaymentService {
	
	public Iterable<RentPayment> findAllByDate(int month, int year);
	
	public Iterable<RentPayment> search(String query);
	
	public RentPayment findById(UUID id);
	
	public void save(RentPayment rent);
	
	public void delete(UUID id);
}
