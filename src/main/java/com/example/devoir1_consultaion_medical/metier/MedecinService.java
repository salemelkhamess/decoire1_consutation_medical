package com.example.devoir1_consultaion_medical.metier;

import com.example.devoir1_consultaion_medical.dao.MedecinDAO;
import com.example.devoir1_consultaion_medical.models.Medecin;

import java.sql.SQLException;
import java.util.List;

public class MedecinService {
    private MedecinDAO medecinDAO;

    public MedecinService() throws SQLException {
        this.medecinDAO = new MedecinDAO();
    }

    public void ajouterMedecin(Medecin medecin) throws SQLException {
        medecinDAO.ajouterMedecin(medecin);
    }

    public List<Medecin> getAllMedecins() throws SQLException {
        return medecinDAO.getAllMedecins();
    }


    public void supprimerMedecin(int id) throws SQLException {
        medecinDAO.supprimerMedecin(id);
    }
}
