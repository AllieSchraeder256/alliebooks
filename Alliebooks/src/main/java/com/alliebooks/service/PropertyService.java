package com.alliebooks.service;

import java.util.UUID;

import com.alliebooks.entities.Property;

public interface PropertyService {
	public Iterable<Property> findAll();

	public void save(Property property);
	
	public Property find(UUID id);
	
	public void delete(UUID id);
}
