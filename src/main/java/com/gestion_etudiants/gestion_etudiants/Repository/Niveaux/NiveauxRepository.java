package com.gestion_etudiants.gestion_etudiants.Repository.Niveaux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

public interface NiveauxRepository extends JpaRepository<Niveaux, Long> {
    
    List<Niveaux> findByFiliereId(Long filiereId);
}
