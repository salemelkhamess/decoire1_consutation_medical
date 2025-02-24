package com.example.devoir1_consultaion_medical.dao;

import com.example.devoir1_consultaion_medical.models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private Connection conn;

    public PatientDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients"; // Assure-toi que le nom de la table est correct

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("telephone"),
                        rs.getDate("date_naissance").toLocalDate()
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public void ajouterPatient(Patient patient) {
        String query = "INSERT INTO patients (nom, prenom, adresse, telephone, date_naissance) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getNom());
            pstmt.setString(2, patient.getPrenom());
            pstmt.setString(3, patient.getAdresse());
            pstmt.setString(4, patient.getTelephone());
            pstmt.setDate(5, Date.valueOf(patient.getDateNaissance()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierPatient(Patient patient) {
        String query = "UPDATE patients SET prenom = ?, adresse = ?, telephone = ?, date_naissance = ? WHERE nom = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patient.getPrenom());
            pstmt.setString(2, patient.getAdresse());
            pstmt.setString(3, patient.getTelephone());
            pstmt.setDate(4, Date.valueOf(patient.getDateNaissance()));
            pstmt.setString(5, patient.getNom()); // Nom utilisé comme clé (Assure-toi qu'il est unique)

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerPatient(String nom) {
        String query = "DELETE FROM patients WHERE nom = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
