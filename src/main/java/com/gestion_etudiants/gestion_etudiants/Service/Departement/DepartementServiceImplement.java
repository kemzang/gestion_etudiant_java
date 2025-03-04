package com.gestion_etudiants.gestion_etudiants.Service.Departement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.Repository.Departement.DepartementRepository;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;

@Service
public class DepartementServiceImplement implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository; // Corrigez ici le type du repository

    @Override
    public List<Departements> ListDepartement() {
        return departementRepository.findAll(); // Appel au repository pour récupérer tous les départements
    }


    @Override
    public List<Departements> findByFaculteId(Long faculteId) {
        return departementRepository.findByFaculteId(faculteId); // Méthode à définir dans le repository
    }

    @Override
    public List<Departements> findAll() {
        return departementRepository.findAll(); // Implémentez la méthode findAll
    }

     // Enregistrer un nouveau département
     @Override
     public Departements save(Departements departement) {
        return departementRepository.save(departement);
    }

    // Mettre à jour un département existant
    @Override
public void update(Long id, Departements departement) {
    Departements existingDepartement = departementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Département non trouvé"));

    // Mettez à jour les champs nécessaires
    existingDepartement.setNom(departement.getNom());

    // Assurez-vous de mettre à jour la faculté si nécessaire
    if (departement.getFaculte() != null) {
        existingDepartement.setFaculte(departement.getFaculte());
    }

    departementRepository.save(existingDepartement);
}

    // Supprimer un département par ID
    @Override
    public void delete(Long id) {
        departementRepository.deleteById(id);
    }
}