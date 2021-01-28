package com.alliebooks.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="properties")
@RestResource
public class Property extends BaseModel {

	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	@OneToMany(mappedBy="property")
	private List<Unit> units;
	
	@OneToMany(mappedBy="property")
	private List<Expense> expenses;
	
	@Transient
	private RoiReport roiReport;
	
	@Transient
	private MultipartFile imageHelper;

	public MultipartFile getImageHelper() {
		return imageHelper;
	}

	public void setImageHelper(MultipartFile imageHelper) {
		this.imageHelper = imageHelper;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public RoiReport getRoiReport() {
		return roiReport;
	}

	public void setRoiReport(RoiReport roiReport) {
		this.roiReport = roiReport;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", units=" + units + "]";
	}
}
