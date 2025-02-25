package com.gestion_etudiants.gestion_etudiants.models.Filiere;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Filiere")
@Getter
@Setter
@NoArgsConstructor
public class Filieres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String nom;

    public Filieres(String nom) {
        this.nom = nom;
        
    }

    @Override
    public String toString() {
        return nom; // Retourne le nom du département
    }
}