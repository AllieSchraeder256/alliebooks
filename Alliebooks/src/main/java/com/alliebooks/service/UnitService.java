package com.alliebooks.service;

import java.util.UUID;

import com.alliebooks.entities.Unit;

public interface UnitService {
	public Iterable<Unit> findAll();
	
	public Unit findById(UUID id);

	public void save(Unit unit);
}
