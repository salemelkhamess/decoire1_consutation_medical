module com.example.devoir1_consultaion_medical {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.devoir1_consultaion_medical to javafx.fxml;
    exports com.example.devoir1_consultaion_medical;
    opens com.example.devoir1_consultaion_medical.presentation to javafx.graphics, javafx.fxml;
    opens com.example.devoir1_consultaion_medical.models to javafx.base;

}

