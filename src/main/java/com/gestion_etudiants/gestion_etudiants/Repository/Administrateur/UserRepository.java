package com.gestion_etudiants.gestion_etudiants.Repository.Administrateur;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_etudiants.gestion_etudiants.models.Administrateur.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMatricule(String matricule); // Remplacez findByUsername par findByMatricule
}