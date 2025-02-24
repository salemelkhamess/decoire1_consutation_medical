package com.example.devoir1_consultaion_medical.models;


import java.sql.Timestamp;

public class Consultation {
    private int id;
    private int patientId;
    private int medecinId;
    private Timestamp dateConsultation;
    private String description;

    public Consultation(int id, int patientId, int medecinId, Timestamp dateConsultation, String description) {
        this.id = id;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dateConsultation = dateConsultation;
        this.description = description;
    }

    public Consultation(int patientId, int medecinId, Timestamp dateConsultation, String description) {
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.dateConsultation = dateConsultation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getMedecinId() {
        return medecinId;
    }

    public Timestamp getDateConsultation() {
        return dateConsultation;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setMedecinId(int medecinId) {
        this.medecinId = medecinId;
    }

    public void setDateConsultation(Timestamp dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
