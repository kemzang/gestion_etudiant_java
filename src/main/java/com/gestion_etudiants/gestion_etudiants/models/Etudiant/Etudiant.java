package com.gestion_etudiants.gestion_etudiants.models.Etudiant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name = "Etudiant")
@Getter
@Setter
@NoArgsConstructor
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nom;
    @Column(length = 50)
    private String prenom;
    @Column(length = 50)
    private String faculte;
    @Column(length = 50)
    private String departement;
    @Column(length = 50)
    private String filiere;
    private int niveau;

    public Etudiant(String nom, String prenom, String faculte, String departement, String filiere, int niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.faculte = faculte;
        this.departement = departement;
        this.filiere = filiere;
        this.niveau = niveau;
    }
    
}
