package com.gestion_etudiants.gestion_etudiants.Service.Filiere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
