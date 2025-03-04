package com.gestion_etudiants.gestion_etudiants.Service.Niveaux;

import java.util.List;

import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

public interface NiveauxService {
    
    List<Niveaux> ListNiveaux();
    List<Niveaux> findByFiliereId(Long filiereId);
    Niveaux ajouterNiveau(Niveaux niveau);
    Niveaux modifierNiveau(Long id, Niveaux niveau);
    void supprimerNiveau(Long id);
}
