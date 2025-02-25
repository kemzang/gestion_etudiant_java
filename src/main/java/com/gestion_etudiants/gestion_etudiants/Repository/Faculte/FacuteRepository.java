package com.gestion_etudiants.gestion_etudiants.Repository.Faculte;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

public interface FacuteRepository  extends JpaRepository<Facultes, Long> {
    
}
