package com.gestion_etudiants.gestion_etudiants.Service.Etudiant;

import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;


// import java.util.List;

public interface EtudiantService {
    Etudiant createEtudiant(Etudiant etudiant);

    java.util.List<Etudiant> listEtudiants();

    byte[] getEtudiantPDFByMatricule(String matricule);

    byte[] getEtudiantPDFByCarteEtudiant(String matricule);

    byte[][] getEtudiantPDFsByMatricule(String matricule);

    public Etudiant findByUsernameAndPasswordAndRole(String matricule, String password, String role);


}