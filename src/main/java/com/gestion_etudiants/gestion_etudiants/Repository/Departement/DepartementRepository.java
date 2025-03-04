package com.gestion_etudiants.gestion_etudiants.Repository.Departement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;

public interface DepartementRepository extends JpaRepository<Departements, Long> {
    
    List<Departements> findByFaculteId(Long faculteId);
}
