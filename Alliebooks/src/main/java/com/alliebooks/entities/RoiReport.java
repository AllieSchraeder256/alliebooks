package com.alliebooks.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoiReport {

	@Id
	private UUID id;
	private double expenses;
	private double income;
	private double roi;
	
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
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	@Override
	public String toString() {
		return "RoiReport [id=" + id + ", expenses=" + expenses + ", income=" + income + ", roi=" + roi + "]";
	}	
}
