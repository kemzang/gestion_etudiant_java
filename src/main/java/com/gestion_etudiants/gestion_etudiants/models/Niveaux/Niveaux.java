package com.gestion_etudiants.gestion_etudiants.models.Niveaux;

import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "niveaux")
@Getter
@Setter
@NoArgsConstructor
public class Niveaux {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique pour le niveau

    @Column(name = "niveau", nullable = false)
    private int niveau; // Niveau de la filière

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false) // Clé étrangère vers la filière
    private Filieres filiere;

    public Niveaux(int niveau, Filieres filiere) {
        this.niveau = niveau;
        this.filiere = filiere;
    }
}
