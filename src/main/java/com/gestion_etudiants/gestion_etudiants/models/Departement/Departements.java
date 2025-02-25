package com.gestion_etudiants.gestion_etudiants.models.Departement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Departement")
@Getter
@Setter
@NoArgsConstructor
public class Departements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String nom;

    public Departements(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom; // Retourne le nom du département
    }
}