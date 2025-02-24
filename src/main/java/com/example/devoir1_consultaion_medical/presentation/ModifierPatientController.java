package com.example.devoir1_consultaion_medical.presentation;

import com.example.devoir1_consultaion_medical.dao.PatientDAO;
import com.example.devoir1_consultaion_medical.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;

public class ModifierPatientController {
    @FXML private TextField txtNom, txtPrenom, txtAdresse, txtTelephone, txtDateNaissance;

    private Patient patient;
    private PatientDAO patientDAO = new PatientDAO();
    private PatientController patientController;

    public void setPatientController(PatientController controller) {
        this.patientController = controller;
    }

    public void initData(Patient patient) {
        this.patient = patient;
        txtNom.setText(patient.getNom());
        txtPrenom.setText(patient.getPrenom());
        txtAdresse.setText(patient.getAdresse());
        txtTelephone.setText(patient.getTelephone());
        txtDateNaissance.setText(patient.getDateNaissance().toString());

        txtNom.setDisable(true); // Empêcher la modification du nom (clé d'identification)
    }

    @FXML
    public void enregistrerModification() {
        patient.setPrenom(txtPrenom.getText());
        patient.setAdresse(txtAdresse.getText());
        patient.setTelephone(txtTelephone.getText());
        patient.setDateNaissance(LocalDate.parse(txtDateNaissance.getText()));

        patientDAO.modifierPatient(patient); // Mise à jour en BD
        patientController.chargerPatientsDepuisBD(); // Rafraîchir la TableView

        // Fermer la fenêtre
        Stage stage = (Stage) txtNom.getScene().getWindow();
        stage.close();
    }
}
