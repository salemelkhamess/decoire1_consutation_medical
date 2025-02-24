package com.example.devoir1_consultaion_medical.presentation;

import com.example.devoir1_consultaion_medical.dao.PatientDAO;
import com.example.devoir1_consultaion_medical.models.Patient;
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
import java.time.LocalDate;
import java.util.List;

public class PatientController {
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient, String> colNom, colPrenom, colAdresse, colTelephone;
    @FXML private TableColumn<Patient, LocalDate> colDateNaissance;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private PatientDAO patientDAO = new PatientDAO();

    @FXML
    public void initialize() {
        // Associer les colonnes aux attributs des patients
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));

        chargerPatientsDepuisBD(); // Charger les patients dès l’ouverture
    }

    public void chargerPatientsDepuisBD() {
        patients.clear();
        patients.addAll(patientDAO.getAllPatients()); // Récupérer les patients de la BD
        tablePatients.setItems(patients); // Associer la liste à la TableView
        tablePatients.refresh(); // Rafraîchir l'affichage
    }

    public void ajouterPatientDansListe(Patient p) {
        patientDAO.ajouterPatient(p); // Insérer en BD
        chargerPatientsDepuisBD(); // Recharger la liste après insertion
    }



    @FXML
    public void ajouterPatient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/devoir1_consultaion_medical/AjoutPatient.fxml"));
            Parent root = loader.load();

            AjoutPatientController controller = loader.getController();
            controller.setPatientController(this);

            Stage stage = new Stage();
            stage.setTitle("Ajouter un Patient");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void modifierPatient() {
        Patient selectedPatient = tablePatients.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/devoir1_consultaion_medical/ModifierPatient.fxml"));
                Parent root = loader.load();

                ModifierPatientController controller = loader.getController();
                controller.setPatientController(this);
                controller.initData(selectedPatient);

                Stage stage = new Stage();
                stage.setTitle("Modifier Patient");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void supprimerPatient() {
        Patient selectedPatient = tablePatients.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            patientDAO.supprimerPatient(selectedPatient.getNom()); // Suppression en BD
            chargerPatientsDepuisBD(); // Recharger la liste
        }
    }



}
