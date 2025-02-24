package com.example.devoir1_consultaion_medical.presentation;

import com.example.devoir1_consultaion_medical.models.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjoutPatientController {
    @FXML private TextField txtNom, txtPrenom, txtAdresse, txtTelephone;
    @FXML private DatePicker dateNaissance;

    private PatientController patientController;

    public void setPatientController(PatientController controller) {
        this.patientController = controller;
    }

    @FXML
    public void enregistrerPatient() {
        if (patientController != null) {
            Patient p = new Patient(txtNom.getText(), txtPrenom.getText(), txtAdresse.getText(),
                    txtTelephone.getText(), dateNaissance.getValue());
            patientController.ajouterPatientDansListe(p);
        }

        // Fermer la fenêtre après l'ajout
        Stage stage = (Stage) txtNom.getScene().getWindow();
        stage.close();
    }
}
