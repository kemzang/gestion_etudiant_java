package com.gestion_etudiants.gestion_etudiants.Service.Niveaux;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.Repository.Niveaux.NiveauxRepository;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

@Service
public class NiveauxServiceImplement implements NiveauxService {

    @Autowired
    private NiveauxRepository niveauxRepository;
    
    @Override
    public List<Niveaux> ListNiveaux() {
        return niveauxRepository.findAll();
    }

    @Override
    public List<Niveaux> findByFiliereId(Long filiereId) {
        return niveauxRepository.findByFiliereId(filiereId); // Assurez-vous que c'est le bon nom de méthode
    }
}
