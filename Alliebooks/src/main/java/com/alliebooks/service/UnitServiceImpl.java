package com.alliebooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.Unit;
import com.alliebooks.repo.UnitRepository;

@Service
public class UnitServiceImpl implements UnitService {
	
	@Autowired
	private UnitRepository unitRepo;

	public Iterable<Unit> findAll() {
		return unitRepo.findAll();
	}

	public void save(Unit unit) {
		unitRepo.save(unit);
	}

}
