package com.example.devoir1_consultaion_medical.dao;

import com.example.devoir1_consultaion_medical.models.Consultation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDAO {
    private Connection connection;

    public ConsultationDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouterConsultation(Consultation consultation) {
        String sql = "INSERT INTO consultations (patient_id, medecin_id, date_consultation, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, consultation.getPatientId());
            stmt.setInt(2, consultation.getMedecinId());
            stmt.setTimestamp(3, consultation.getDateConsultation());
            stmt.setString(4, consultation.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM  consultations";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                consultations.add(new Consultation(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("medecin_id"),
                        rs.getTimestamp("date_consultation"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }


    public void supprimerConsultation(int id) {
        String sql = "DELETE FROM consultations WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
