package com.gestion_etudiants.gestion_etudiants.models.Faculte;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Faculte")
@Getter
@Setter
@NoArgsConstructor
public class Facultes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String nom;

    public Facultes(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom; // Retourne le nom de la faculté
    }
}