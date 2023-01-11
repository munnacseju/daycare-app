package com.daycare.app.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "baby")
public class Baby {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baby_id")
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@NotNull
	String babyName;
	
	@NotNull
	int babyAge;
	
	String motherName;
	String fatherName;
	String contactNumber;
	String address;
	String fevoriteFood;
	Boolean isSicked = false;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Baby() {};
	public Baby(Long id, User user, @NotNull String babyName, @NotNull int babyAge, String motherName,
			String fatherName, String contactNumber, String address, String fevoriteFood, Boolean isSicked) {
		super();
		this.id = id;
		this.user = user;
		this.babyName = babyName;
		this.babyAge = babyAge;
		this.motherName = motherName;
		this.fatherName = fatherName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.fevoriteFood = fevoriteFood;
		this.isSicked = isSicked;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBabyName() {
		return babyName;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public int getBabyAge() {
		return babyAge;
	}
	public void setBabyAge(int babyAge) {
		this.babyAge = babyAge;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFevoriteFood() {
		return fevoriteFood;
	}
	public void setFevoriteFood(String fevoriteFood) {
		this.fevoriteFood = fevoriteFood;
	}
	public Boolean getIsSicked() {
		return isSicked;
	}
	public void setIsSicked(Boolean isSicked) {
		this.isSicked = isSicked;
	}
}
