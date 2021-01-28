package com.alliebooks.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class RoiReport {

	@Id
	@Type(type="uuid-char")
	private UUID id;
	private double expenses;
	private double roi;
	private double noi;
	
	@Column(name="operating_expenses")
	private double operatingExpenses;
	
	@Column(name="operating_income")
	private double operatingIncome;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	
	public double getOperatingExpenses() {
		return operatingExpenses;
	}
	public void setOperatingExpenses(double operatingExpenses) {
		this.operatingExpenses = operatingExpenses;
	}
	public double getOperatingIncome() {
		return operatingIncome;
	}
	public void setOperatingIncome(double operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getNoi() {
		return noi;
	}
	public void setNoi(double noi) {
		this.noi = noi;
	}
	
}
