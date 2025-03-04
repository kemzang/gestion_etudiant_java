package com.gestion_etudiants.gestion_etudiants.models.Filiere;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements; // Importer la classe Departements

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

    @ManyToOne // Indique qu'une filière appartient à un département
    @JoinColumn(name = "departement_id", nullable = false) // Nom de la colonne dans la table Filiere
    private Departements departement; // Clé étrangère vers le département

    public Filieres(String nom, Departements departement) {
        this.nom = nom;
        this.departement = departement; // Modifiez le constructeur
    }

    @Override
    public String toString() {
        return nom; // Retourne le nom de la filière
    }
}