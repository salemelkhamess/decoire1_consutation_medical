package com.example.devoir1_consultaion_medical.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/devoir1_consultaion_medical/main.fxml"));

        BorderPane root = loader.load(); // Correction ici

        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion des Consultations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
