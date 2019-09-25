package com.alliebooks.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alliebooks.entities.Expense;
import com.alliebooks.entities.Property;
import com.alliebooks.entities.RentPayment;

@Service
public class ImageServiceImpl implements ImageService {

	public static final String IMAGE_FILE_LOCATION = "/Users/Allie/Desktop/alliebooks-receipts";
	
	public InputStream fetchImage(String fileName) {
		File file = new File(IMAGE_FILE_LOCATION + "/" + fileName);
		try {
			if(file.exists()) {
				return new FileInputStream(file);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}

	public void saveImage(Expense expense) {
		MultipartFile mpf = expense.getImageHelper();
		if (mpf.getSize() > 0) {
			if (expense.getId() == null) {
				expense.setId(UUID.randomUUID());
			}
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
			String fileName = cleanUpFileName(String.format("%s_%s_%s", fmt.format(expense.getDate()), expense.getStore(), expense.getId().toString()), mpf);
			writeFile(mpf, fileName);
			expense.setReceiptImage(fileName);
		}
	}

	public void saveImage(RentPayment rent) {
		MultipartFile mpf = rent.getImageHelper();
		if (mpf.getSize() > 0) {
			if (rent.getId() == null) {
				rent.setId(UUID.randomUUID());
			}
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
			String fileName = cleanUpFileName(String.format("%s_%s_%s", fmt.format(rent.getDate()), rent.getTenant(), rent.getId().toString()), mpf);
			writeFile(mpf, fileName);
			rent.setPaymentImage(fileName);
		}
	}
	
	public void saveImage(Property property) {
		MultipartFile mpf = property.getImageHelper();
		if (mpf.getSize() > 0) {
			String fileName = cleanUpFileName(property.getName(), mpf);
			writeFile(mpf, fileName);
			property.setImage(fileName);
		}
	}

	private void writeFile(MultipartFile mpf, String fileName) {
		File file = new File(IMAGE_FILE_LOCATION +"/"+ fileName);
		try {
			file.createNewFile();
			mpf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String cleanUpFileName(String file, MultipartFile mpf) {
		return file.replace(" ", "_") + "."+ FilenameUtils.getExtension(mpf.getOriginalFilename());
	}
}
