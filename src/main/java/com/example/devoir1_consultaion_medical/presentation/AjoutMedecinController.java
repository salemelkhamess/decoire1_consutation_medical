package com.example.devoir1_consultaion_medical.presentation;


import com.example.devoir1_consultaion_medical.metier.MedecinService;
import com.example.devoir1_consultaion_medical.models.Medecin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AjoutMedecinController {
    @FXML
    private TextField nomField;

    @FXML
    private TextField specialiteField;

    private final MedecinService medecinService = new MedecinService();
    PatientController patientController = new PatientController();

    public AjoutMedecinController() throws SQLException {
    }

    @FXML
    private void ajouterMedecin() throws SQLException {
        String nom = nomField.getText();
        String specialite = specialiteField.getText();

        if (nom.isEmpty() || specialite.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        Medecin medecin = new Medecin(nom, specialite);
        medecinService.ajouterMedecin(medecin);

        showAlert("Succès", "Médecin ajouté avec succès !");

        // Fermer la fenêtre après l'ajout
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void fermerFenetre() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
