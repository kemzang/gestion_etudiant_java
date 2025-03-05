package com.gestion_etudiants.gestion_etudiants.Repository.Etudiant;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    
    Optional<Etudiant> findByMatricule(String matricule);
}
