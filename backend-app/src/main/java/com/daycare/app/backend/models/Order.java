package com.daycare.app.backend.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "caregiver_order")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
    public Order(Long id, User user, Long babyId, Long caregiverId, Long amount, Timestamp startTime, Timestamp endTime,
			boolean isPaymentDone, boolean isServiceDone, String speciality) {
		this.id = id;
		this.user = user;
		this.babyId = babyId;
		this.caregiverId = caregiverId;
		this.amount = amount;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isPaymentDone = isPaymentDone;
		this.isServiceDone = isServiceDone;
		this.speciality = speciality;
	}
	@Column(name = "baby_id", nullable = false)
    private Long babyId;
	
    @Column(name = "caregiver_id")
    private Long caregiverId;
	private Long amount;
	
	public void setBabyId(Long babyId) {
		this.babyId = babyId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp startTime = new Timestamp(System.currentTimeMillis());
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp endTime = new Timestamp(System.currentTimeMillis());
	
	private boolean isPaymentDone = false;
	private boolean isServiceDone = false;
	private String speciality;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getBabyId() {
		return babyId;
	}
	public void setBaby(Long babyId) {
		this.babyId = babyId;
	}
	public Long getCaregiverId() {
		return caregiverId;
	}
	public void setCaregiverId(Long caregiverId) {
		this.caregiverId = caregiverId;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public boolean isPaymentDone() {
		return isPaymentDone;
	}
	public void setPaymentDone(boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}
	public boolean isServiceDone() {
		return isServiceDone;
	}
	public void setServiceDone(boolean isServiceDone) {
		this.isServiceDone = isServiceDone;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Order(Long id, User user, Long babyId,Long caregiverId, Timestamp startTime, Timestamp endTime,
			boolean isPaymentDone, boolean isServiceDone, String speciality) {
		this.id = id;
		this.user = user;
		this.babyId = babyId;
		this.caregiverId = caregiverId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isPaymentDone = isPaymentDone;
		this.isServiceDone = isServiceDone;
		this.speciality = speciality;
	}
	public Order() {
	}	

}
