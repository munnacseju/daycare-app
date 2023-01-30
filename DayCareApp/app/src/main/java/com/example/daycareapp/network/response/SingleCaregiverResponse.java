package com.example.daycareapp.network.response;

import com.example.daycareapp.models.Caregiver;

public class SingleCaregiverResponse {
    private String message, status;
    private Caregiver caregiver;

    public SingleCaregiverResponse() {
    }

    public SingleCaregiverResponse(String message, String status, Caregiver caregiver) {
        this.message = message;
        this.status = status;
        this.caregiver = caregiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
}
