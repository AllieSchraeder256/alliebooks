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
@Table(name="rent_payments")
@RestResource
public class RentPayment {
	@Column(name="id", updatable = false, nullable = false)
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name="tenant")
	private String tenant;
	
	@Column(name="note")
	private String note;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="unit_id")
	private UUID unitId;
	
	@ManyToOne
	@JoinColumn(name="unit_id", insertable=false, updatable=false)
	private Unit unit;
	
	@Column(name="payment_image")
	private String paymentImage;
	
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getPaymentImage() {
		return paymentImage;
	}

	public void setPaymentImage(String paymentImage) {
		this.paymentImage = paymentImage;
	}

	public UUID getUnitId() {
		return unitId;
	}

	public void setUnitId(UUID unitId) {
		this.unitId = unitId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "RentPayment [id=" + id + ", tenant=" + tenant + ", note=" + note + ", date=" + date + ", amount=" + amount + "]";
	}
}
