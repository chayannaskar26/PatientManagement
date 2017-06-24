package com.example.chayannaskar.patientmanagement;

/**
 * Created by chayannaskar on 23/06/17.
 */

public class Prescription {
    private String disease;
    private String drugs;

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public Prescription(String disease, String drugs) {
        this.disease = disease;
        this.drugs = drugs;
    }

    public Prescription() {
    }
}

