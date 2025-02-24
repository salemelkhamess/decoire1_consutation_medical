package com.example.devoir1_consultaion_medical.presentation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

import java.io.IOException;

public class MainController {
    @FXML
    private VBox contenuZone;

    public void afficherPatients() {
        chargerVue("/com/example/devoir1_consultaion_medical/patient.fxml");
    }

    public void afficherMedecins() {
        chargerVue("/com/example/devoir1_consultaion_medical/medecin.fxml");
    }

    public void afficherConsultations() {
        chargerVue("/com/example/devoir1_consultaion_medical/consultation.fxml");
    }

    private void chargerVue(String fxml) {
        try {
            Node node = FXMLLoader.load(getClass().getResource(fxml));
            contenuZone.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
