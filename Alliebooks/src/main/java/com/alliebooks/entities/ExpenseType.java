package com.alliebooks.entities;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name="expense_types")
@RestResource
public class ExpenseType extends BaseModel {
	
	@Column(name="name")
	private String name;
	
	@Column(name="active")
	private Boolean active;
	
	@OneToMany(mappedBy="expenseType")
	private List<Expense> expenses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "ExpenseType [name=" + name + "]";
	}
}
