package com.alliebooks.service;

import java.io.InputStream;

import com.alliebooks.entities.Expense;
import com.alliebooks.entities.Property;
import com.alliebooks.entities.RentPayment;

public interface ImageService {

	public InputStream fetchImage(String fileName);
	
	public void saveImage(Expense expense);
	
	public void saveImage(Property property);
	
	public void saveImage(RentPayment rent);
}
