package com.example.devoir1_consultaion_medical.presentation;

import com.example.devoir1_consultaion_medical.dao.ConsultationDAO;
import com.example.devoir1_consultaion_medical.dao.DatabaseConnection;
import com.example.devoir1_consultaion_medical.models.Consultation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConsultationController {

    @FXML
    private ComboBox<Patient> cmbPatientId;
    @FXML
    private ComboBox<Medecin> cmbMedecinId;
    @FXML
    private DatePicker dateConsultation;
    @FXML
    private TextField txtDescription;

    @FXML
    private TableView<Consultation> tableConsultations;
    @FXML
    private TableColumn<Consultation, String> colPatient;
    @FXML
    private TableColumn<Consultation, String> colMedecin;
    @FXML
    private TableColumn<Consultation, String> colDate;
    @FXML
    private TableColumn<Consultation, String> colDescription;

    private ObservableList<Consultation> consultationsList = FXCollections.observableArrayList();


    private ConsultationDAO consultationDAO;

    public void initialize() {
        consultationDAO = new ConsultationDAO();
        chargerPatients();
        chargerMedecins();

        colPatient.setCellValueFactory(cellData ->
                new SimpleStringProperty(getPatientNameById(cellData.getValue().getPatientId())));
        colMedecin.setCellValueFactory(cellData ->
                new SimpleStringProperty(getMedecinNameById(cellData.getValue().getMedecinId())));
        colDate.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDateConsultation().toString()));
        colDescription.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription()));

        tableConsultations.setItems(consultationsList);
        chargerConsultations();

    }
    private void chargerConsultations() {
        consultationsList.clear();
        String sql = "SELECT * FROM consultations";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consultation consultation = new Consultation(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("medecin_id"),
                        rs.getTimestamp("date_consultation"),
                        rs.getString("description")
                );
                consultationsList.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getPatientNameById(int id) {
        for (Patient p : cmbPatientId.getItems()) {
            if (p.getId() == id) return p.toString();
        }
        return "Inconnu";
    }

    private String getMedecinNameById(int id) {
        for (Medecin m : cmbMedecinId.getItems()) {
            if (m.getId() == id) return m.toString();
        }
        return "Inconnu";
    }


    private void chargerPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        String sql = "SELECT id, nom FROM patients";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(new Patient(rs.getInt("id"), rs.getString("nom")));
            }
            cmbPatientId.setItems(patients);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chargerMedecins() {
        ObservableList<Medecin> medecins = FXCollections.observableArrayList();
        String sql = "SELECT id, nom FROM medecins";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                medecins.add(new Medecin(rs.getInt("id"), rs.getString("nom")));
            }
            cmbMedecinId.setItems(medecins);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouterConsultation() {
        Patient patient = cmbPatientId.getValue();
        Medecin medecin = cmbMedecinId.getValue();
        LocalDate localDate = dateConsultation.getValue();
        String description = txtDescription.getText();

        if (patient == null || medecin == null || localDate == null || description.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Timestamp timestamp = new Timestamp(date.getTime());

        Consultation consultation = new Consultation(0, patient.getId(), medecin.getId(), timestamp, description);
        consultationDAO.ajouterConsultation(consultation);
        showAlert("Succès", "Consultation ajoutée avec succès.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Classes internes pour représenter les patients et les médecins
    public static class Patient {
        private int id;
        private String nom;

        public Patient(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nom; // Affiche le nom dans le ComboBox
        }
    }

    public static class Medecin {
        private int id;
        private String nom;

        public Medecin(int id, String nom) {
            this.id = id;
            this.nom = nom;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nom; // Affiche le nom dans le ComboBox
        }
    }



    @FXML
    private Button btnSupprimer;

    @FXML
    private void supprimerConsultation() {
        Consultation consultationSelectionnee = tableConsultations.getSelectionModel().getSelectedItem();

        if (consultationSelectionnee == null) {
            showAlert("Erreur", "Veuillez sélectionner une consultation à supprimer.");
            return;
        }

        int consultationId = consultationSelectionnee.getId();

        // Supprimer de la base de données via DAO
        consultationDAO.supprimerConsultation(consultationId);

        // Supprimer de la TableView
        tableConsultations.getItems().remove(consultationSelectionnee);

        showAlert("Succès", "Consultation supprimée avec succès.");
    }
}
