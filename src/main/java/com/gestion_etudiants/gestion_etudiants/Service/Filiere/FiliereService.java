package com.gestion_etudiants.gestion_etudiants.Service.Filiere;

import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

import java.util.List;

public interface FiliereService {
    List<Filieres> ListFiliere();
    List<Filieres> findByDepartementId(Long departementId);
    Filieres ajouterFiliere(Filieres filiere);
    Filieres modifierFiliere(Long id, Filieres filiere);
    void supprimerFiliere(Long id);
}
