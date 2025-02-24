package com.example.devoir1_consultaion_medical.dao;

import com.example.devoir1_consultaion_medical.models.Medecin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinDAO {
    private Connection conn;

    public MedecinDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    // 🔹 Ajouter un médecin
    public void ajouterMedecin(Medecin medecin) {
        String query = "INSERT INTO medecins (nom, specialite) VALUES ( ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, medecin.getNom());
            pstmt.setString(2, medecin.getSpecialite());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Récupérer tous les médecins
    public List<Medecin> getAllMedecins() {
        List<Medecin> medecins = new ArrayList<>();
        String query = "SELECT * FROM medecins";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Medecin medecin = new Medecin(rs.getInt("id"), rs.getString("nom"), rs.getString("specialite"));
                medecins.add(medecin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medecins;
    }

    // 🔹 Modifier un médecin
    public void modifierMedecin(Medecin medecin) {
        String query = "UPDATE medecins SET nom=? specialite=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, medecin.getNom());
            pstmt.setString(2, medecin.getSpecialite());
            pstmt.setInt(3, medecin.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 Supprimer un médecin
    public void supprimerMedecin(int id) {
        String query = "DELETE FROM medecins WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
