package com.example.devoir1_consultaion_medical.presentation;

import com.example.devoir1_consultaion_medical.dao.MedecinDAO;
import com.example.devoir1_consultaion_medical.models.Medecin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class MedecinController {
    @FXML private TableView<Medecin> tableMedecins;
    @FXML private TableColumn<Medecin, String> colNom, colPrenom, colSpecialite, colTelephone;

    private ObservableList<Medecin> medecins = FXCollections.observableArrayList();
    private MedecinDAO medecinDAO = new MedecinDAO();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        chargerMedecinsDepuisBD();
    }


    private void chargerMedecinsDepuisBD() {
        medecins.clear();
        List<Medecin> listeMedecins = medecinDAO.getAllMedecins();

        System.out.println("Médecins récupérés : " + listeMedecins.size());

        medecins.addAll(listeMedecins);
        tableMedecins.setItems(medecins);
        tableMedecins.refresh();
    }


    @FXML
    public void ajouterMedecin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/devoir1_consultaion_medical/AjoutMedecin.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un Médecin");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void supprimerMedecin() {
        Medecin selectedMedecin = tableMedecins.getSelectionModel().getSelectedItem();
        if (selectedMedecin != null) {
            medecinDAO.supprimerMedecin(selectedMedecin.getId());
            chargerMedecinsDepuisBD();
        }
    }
}
