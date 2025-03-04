package com.gestion_etudiants.gestion_etudiants.Service.Filiere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.Repository.Filiere.FiliereRepository;

@Service
public class FiliereServiceImplement implements FiliereService{

    @Autowired
    private FiliereRepository filiereRepository;
    
    @Override
    public List<Filieres> ListFiliere() {
        return filiereRepository.findAll();
    }

    @Override
    public List<Filieres> findByDepartementId(Long departementId) {
        return filiereRepository.findByDepartementId(departementId); // Méthode à définir dans le repository
    }

    @Override
    public Filieres ajouterFiliere(Filieres filiere) {
        // Validation des données
        if (filiere.getNom() == null || filiere.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom de la filière ne peut pas être vide.");
        }
        return filiereRepository.save(filiere);
    }

    @Override
    public Filieres modifierFiliere(Long id, Filieres filiere) {
        filiere.setId(id);
        return filiereRepository.save(filiere);
    }

    @Override
    public void supprimerFiliere(Long id) {
        try {
            filiereRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Filière non trouvée avec l'ID : " + id);
        }
    }
}
