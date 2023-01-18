package com.daycare.app.backend.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	public Caregiver(Long id, User user, String caregiverMotherName, String adminFeedBack, String address,
			String imageBase64, String speciality) {
		this.id = id;
		this.user = user;
		this.caregiverMotherName = caregiverMotherName;
		this.adminFeedBack = adminFeedBack;
		this.address = address;
		this.imageBase64 = imageBase64;
		this.speciality = speciality;
	}

	private String caregiverMotherName;

	public String getAdminFeedBack() {
		return adminFeedBack;
	}

	public void setAdminFeedBack(String adminFeedBack) {
		this.adminFeedBack = adminFeedBack;
	}

	private String adminFeedBack;
	
	private String address;
	public Caregiver(Long id, User user, String caregiverMotherName, String address, String imageBase64,
			String speciality) {
		this.id = id;
		this.user = user;
		this.caregiverMotherName = caregiverMotherName;
		this.address = address;
		this.imageBase64 = imageBase64;
		this.speciality = speciality;
	}

	private String imageBase64;

	

	public Caregiver(Long id, User user, String caregiverMotherName, String address, String speciality) {
		this.id = id;
		this.user = user;
		this.caregiverMotherName = caregiverMotherName;
		this.address = address;
		this.speciality = speciality;
	}

	private String speciality;
	
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "caregiver_rivews", joinColumns = {
//            @JoinColumn(name = "caregiver_id", nullable = false, updatable = false) }, inverseJoinColumns = {
//                    @JoinColumn(name = "review_id", nullable = false, updatable = false) })
//    private List<Review> reviews;

    public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Caregiver(){}
	
	public Caregiver(Long id, User user, String caregiverMotherName, String address) {
		this.id = id;
		this.user = user;
		this.caregiverMotherName = caregiverMotherName;
		this.address = address;
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
}
