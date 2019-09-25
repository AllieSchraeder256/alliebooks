package com.alliebooks.service;

import com.alliebooks.entities.Unit;

public interface UnitService {
	public Iterable<Unit> findAll();

	public void save(Unit unit);
}
