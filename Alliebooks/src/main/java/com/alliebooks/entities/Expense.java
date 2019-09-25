package com.alliebooks.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="expenses")
@RestResource
public class Expense {
	@Column(name="id", updatable = false, nullable = false)
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name="store")
	private String store;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="receipt_image")
	private String receiptImage;
	
	@Column(name="expense_type_id")
	private UUID expenseTypeId;
	
	@ManyToOne
	@JoinColumn(name="expense_type_id", insertable=false, updatable=false)
	private ExpenseType expenseType;

	@Column(name="property_id")
	private UUID propertyId;
	
	@ManyToOne
	@JoinColumn(name="property_id", insertable=false, updatable=false)
	private Property property;
	
	@Transient
	private MultipartFile imageHelper;

	public MultipartFile getImageHelper() {
		return imageHelper;
	}

	public void setImageHelper(MultipartFile imageHelper) {
		this.imageHelper = imageHelper;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReceiptImage() {
		return receiptImage;
	}

	public void setReceiptImage(String recieptImage) {
		this.receiptImage = recieptImage;
	}

	public UUID getExpenseTypeId() {
		return expenseTypeId;
	}

	public void setExpenseTypeId(UUID expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}
	

	public UUID getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(UUID propertyId) {
		this.propertyId = propertyId;
	}
	

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", store=" + store + ", comment=" + comment + ", date=" + date + ", amount=" + amount + ", expenseTypeId=" + expenseTypeId
				+ "]";
	}
}
