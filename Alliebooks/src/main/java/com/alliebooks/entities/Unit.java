package com.alliebooks.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name="units")
@RestResource
public class Unit extends BaseModel {
	
	@Column(name="name")
	private String name;
	
	@Column(name="current_tenant")
	private String currentTenant;
	
	@Column(name="current_rent")
	private double currentRent;
	
	@Column(name="property_id", insertable=false, updatable=false)
	//@Type(type="uuid-char")
	private UUID propertyId;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@OneToMany(mappedBy="unit")
	private List<RentPayment> rentPayments;

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(UUID propertyId) {
		this.propertyId = propertyId;
	}

	public String getCurrentTenant() {
		return currentTenant;
	}

	public void setCurrentTenant(String currentTenant) {
		this.currentTenant = currentTenant;
	}

	public double getCurrentRent() {
		return currentRent;
	}

	public void setCurrentRent(double currentRent) {
		this.currentRent = currentRent;
	}

	public List<RentPayment> getRentPayments() {
		return rentPayments;
	}

	public void setRentPayments(List<RentPayment> rentPayments) {
		this.rentPayments = rentPayments;
	}

	@Override
	public String toString() {
		return "Unit [name=" + name + ", currentTenant=" + currentTenant + ", currentRent=" + currentRent + ", propertyId=" + propertyId +"]";
	}
	
}
