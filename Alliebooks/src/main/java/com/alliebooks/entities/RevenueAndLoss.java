package com.alliebooks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name="v_revenue_and_loss")
@Immutable
public class RevenueAndLoss {
	
	@Id
	@Column(name="property_name")
	private String propertyName;
	
	@Column(name="revenue")
	private Double revenue;
	
	@Column(name="expenses")
	private Double expenses;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="spread")
	private Double spread;
	
	@Transient//@Column(name="vacancy")//TODO finish
	private Double vacancy;
	
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double profit) {
		this.revenue = profit;
	}
	public Double getExpenses() {
		return expenses;
	}
	public void setExpenses(Double loss) {
		this.expenses = loss;
	}
	public Double getSpread() {
		return spread;
	}
	public void setSpread(Double spread) {
		this.spread = spread;
	}
	public Double getVacancy() {
		return vacancy;
	}
	public void setVacancy(Double vacancy) {
		this.vacancy = vacancy;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
