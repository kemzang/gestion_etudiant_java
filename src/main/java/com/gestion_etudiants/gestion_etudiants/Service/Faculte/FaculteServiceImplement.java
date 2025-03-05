package com.gestion_etudiants.gestion_etudiants.Service.Faculte;

import com.gestion_etudiants.gestion_etudiants.Repository.Faculte.FacuteRepository;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaculteServiceImplement implements FaculteService {

    @Autowired
    private FacuteRepository faculteRepository; // Injection du repository

    @Override
    public void saveFaculte(Facultes faculte) {
        faculteRepository.save(faculte); // Sauvegarde la faculté dans la base de données
    }

    @Override
    public List<Facultes> ListFaculte() {
        return faculteRepository.findAll(); // Appel au repository pour récupérer toutes les facultés
    }

    @Override
    public void deleteFaculte(Long id) {
        faculteRepository.deleteById(id); // Suppression de la faculté par ID
    }

    @Override
    public Facultes findById(Long id) {
        return faculteRepository.findById(id).orElse(null); // Retourne la faculté ou null si non trouvée
    }
}