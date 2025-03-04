package com.gestion_etudiants.gestion_etudiants.models.Departement;

import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

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

    @ManyToOne // Indique qu'un département appartient à une faculté
    @JoinColumn(name = "faculte_id", nullable = false) // Nom de la colonne dans la table Departement
    private Facultes faculte; // Clé étrangère vers la faculté

    public Departements(String nom, Facultes faculte) {
        this.nom = nom;
        this.faculte = faculte; // Modifiez le constructeur
    }

    @Override
    public String toString() {
        return nom; // Retourne le nom du département
    }
}