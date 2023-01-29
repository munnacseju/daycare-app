package com.daycare.app.backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "caregiver")
public class Caregiver {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caregiver_id")
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

	private Boolean isAvailable = true;
	private String caregiverMotherName;

	private String adminFeedBack;
	
	private String address;
	
    @Column(columnDefinition = "TEXT")
	private String imageBase64;

	private String speciality;

	public Caregiver(){}
	
	public Caregiver(Long id, User user, Boolean isAvailable, String caregiverMotherName, String adminFeedBack,
			String address, String imageBase64, String speciality) {
		this.id = id;
		this.user = user;
		this.isAvailable = isAvailable;
		this.caregiverMotherName = caregiverMotherName;
		this.adminFeedBack = adminFeedBack;
		this.address = address;
		this.imageBase64 = imageBase64;
		this.speciality = speciality;
	}


	public String getAdminFeedBack() {
		return adminFeedBack;
	}

	public void setAdminFeedBack(String adminFeedBack) {
		this.adminFeedBack = adminFeedBack;
	}

    public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}	

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

	public String getCaregiverMotherName() {
		return caregiverMotherName;
	}

	public void setCaregiverMotherName(String caregiverMotherName) {
		this.caregiverMotherName = caregiverMotherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
