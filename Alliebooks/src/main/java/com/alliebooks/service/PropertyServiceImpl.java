package com.alliebooks.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alliebooks.entities.Property;
import com.alliebooks.entities.Unit;
import com.alliebooks.repo.PropertyRepository;
import com.alliebooks.repo.UnitRepository;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private UnitRepository unitRepo;
	
	@Autowired
	private ImageService imageService;

	public Iterable<Property> findAll() {
		return propertyRepo.findAll();
	}

	public void save(Property property) {
		imageService.saveImage(property);
		propertyRepo.save(property);
		for(Unit u : property.getUnits()) {
			u.setPropertyId(property.getId());
			u.setProperty(property);
			unitRepo.save(u);
		}
	}

	public Property find(UUID id) {
		return propertyRepo.findById(id).get();
	}

	public void delete(UUID id) {
		Optional<Property> property = propertyRepo.findById(id);
		if (property.isPresent()) {
			unitRepo.deleteAll(property.get().getUnits());
		}
		propertyRepo.deleteById(id);
	}

}
