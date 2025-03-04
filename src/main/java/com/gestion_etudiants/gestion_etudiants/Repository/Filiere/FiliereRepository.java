package com.gestion_etudiants.gestion_etudiants.Repository.Filiere;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

public interface FiliereRepository extends JpaRepository<Filieres, Long> {
    List<Filieres> findByDepartementId(Long departementId); 
}
