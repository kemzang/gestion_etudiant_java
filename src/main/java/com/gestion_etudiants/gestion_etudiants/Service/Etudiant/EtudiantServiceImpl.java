package com.gestion_etudiants.gestion_etudiants.Service.Etudiant;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.Repository.EtudiantRepository;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    
    @Override
    public Etudiant createEtudiant(Etudiant etudiant) {
        // TODO Auto-generated method stub
        return etudiantRepository.save(etudiant);
    }
}
