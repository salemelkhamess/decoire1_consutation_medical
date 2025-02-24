package com.example.devoir1_consultaion_medical.models;

import java.time.LocalDate;

public class Medecin {
    private int id;
    private String nom;
    private String specialite;

    // Constructeur
    public Medecin(int id, String nom, String specialite) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
    }

    public Medecin(String nom,  String specialite) {
        this.nom = nom;
        this.specialite = specialite;
    }


    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }


    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }


}
