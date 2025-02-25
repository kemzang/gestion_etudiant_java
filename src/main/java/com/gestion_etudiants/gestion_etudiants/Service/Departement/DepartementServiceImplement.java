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
}