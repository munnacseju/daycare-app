package com.example.daycareapp.models;

public class Caregiver {
    private Long id;

    public Caregiver() {
    }

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

    private User user;
    private String caregiverMotherName;
    private String address;
//    private String imagefileText;
}
