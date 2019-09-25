package com.alliebooks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

@Immutable
@Entity
@Table(name="v_expenses_by_property")
public class ExpenseByProperty {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="property_name")
	private String propertyName;
	
	@Column(name="expense_type")
	private String expenseTypeName;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="average") //TODO calculate this
	private Double average;
	
	
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getExpenseTypeName() {
		return expenseTypeName;
	}
	public void setExpenseTypeName(String expenseTypeName) {
		this.expenseTypeName = expenseTypeName;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ExpenseByProperty [propertyName=" + getPropertyName() + ", expenseTypeName=" + getExpenseTypeName() + ", amount=" + amount +", year=" + getYear() + ", average=" + average + "]";
	}
	
	
}
