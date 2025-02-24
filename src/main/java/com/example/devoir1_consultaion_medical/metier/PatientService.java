package com.example.devoir1_consultaion_medical.metier;

import com.example.devoir1_consultaion_medical.dao.PatientDAO;
import com.example.devoir1_consultaion_medical.models.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService {
    private PatientDAO patientDAO;

    public PatientService() throws SQLException {
        this.patientDAO = new PatientDAO();
    }

    public void ajouterPatient(Patient patient) throws SQLException {
        patientDAO.ajouterPatient(patient);
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientDAO.getAllPatients();
    }


}
