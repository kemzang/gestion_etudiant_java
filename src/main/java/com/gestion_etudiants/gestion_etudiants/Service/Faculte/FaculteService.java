package com.gestion_etudiants.gestion_etudiants.Service.Faculte;

import java.util.List;

import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

public interface FaculteService {
    List<Facultes> ListFaculte();
    void saveFaculte(Facultes faculte);
    void deleteFaculte(Long id);
    Facultes findById(Long id);
}
