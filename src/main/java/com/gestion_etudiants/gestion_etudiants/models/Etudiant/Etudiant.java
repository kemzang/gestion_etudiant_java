package com.gestion_etudiants.gestion_etudiants.models.Etudiant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String dateNaissance;
    @Column(length = 50)
    private String lieuNaissance;
    @Column(length = 50)
    private String matricule;
    @Column(length = 50)
    private String anneeAcademique;
    @Column(length = 50)
    private String faculte;
    @Column(length = 50)
    private String departement;
    @Column(length = 50)
    private String filiere;
    private int niveau;
    private int payement;

    @Lob // Champ pour stocker la photo
    private byte[] photo;

    @Lob // Champ pour stocker la photo par défaut
    private byte[] photoDefaut;

    @Lob // Large OBject pour stocker des données binaires
    private byte[] pdfAttestationScolarite;

    @Lob
    private byte[] pdfCarteEtudiant;

    // Constructeur avec les PDF et la photo
    public Etudiant(String nom, String prenom, String dateNaissance, String lieuNaissance, String matricule, String anneeAcademique, String faculte, String departement, String filiere, int niveau, int payement, byte[] pdfAttestationScolarite, byte[] pdfCarteEtudiant, byte[] photo, byte[] photoDefaut) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.matricule = matricule;
        this.anneeAcademique = anneeAcademique;
        this.faculte = faculte;
        this.departement = departement;
        this.filiere = filiere;
        this.niveau = niveau;
        this.payement = payement;
        this.photo = photo;
        this.photoDefaut = photoDefaut;
        this.pdfAttestationScolarite = pdfAttestationScolarite;
        this.pdfCarteEtudiant = pdfCarteEtudiant;
        
    }
}
