package com.example.daycareapp.models;

public class Caregiver {

    private String name;
    private String location;
    private String imagefileText;

    public Caregiver(String name, String location, String imagefileText) {
        this.name = name;
        this.location = location;;
        this.imagefileText = imagefileText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagefileText() {
        return imagefileText;
    }

    public void setImagefileText(String imagefileText) {
        this.imagefileText = imagefileText;
    }
}
