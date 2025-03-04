package com.gestion_etudiants.gestion_etudiants.Service.Departement;

import java.util.List;

import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;

public interface DepartementService {
    
    List<Departements> ListDepartement();
    List<Departements> findByFaculteId(Long faculteId);
    List<Departements> findAll();
    Departements save(Departements departement);
    void update(Long id, Departements departement);
    void delete(Long id);
}
