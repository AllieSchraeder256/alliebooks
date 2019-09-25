package com.alliebooks.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alliebooks.Utils;
import com.alliebooks.entities.RentPayment;
import com.alliebooks.repo.RentPaymentRepository;

@Component
public class RentPaymentServiceImpl implements RentPaymentService {

	private static final String[] SEARCH_FIELDS = new String[] {"tenant", "note", "year"};//TODO add unit

	@Autowired
	private RentPaymentRepository repository;
	
	@Autowired
	private ImageService imageService;
	
	public Iterable<RentPayment> findAllByDate(int month, int year) {
		Iterable<RentPayment> list = repository.findAllByDate(month, year);
		return list;
	}

	public void save(RentPayment rent) {
		imageService.saveImage(rent);
		repository.save(rent);
	}
	
	public RentPayment findById(UUID id) {
		return repository.findById(id).get();
	}

	public void delete(UUID id) {
		repository.deleteById(id);
	}

	public Iterable<RentPayment> search(String query) {
		return repository.search(Utils.getSearchCriteria(query, SEARCH_FIELDS));
	}

}
