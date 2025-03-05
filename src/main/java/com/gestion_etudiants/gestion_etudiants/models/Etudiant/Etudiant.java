package com.gestion_etudiants.gestion_etudiants.models.Etudiant;

import java.time.LocalDate;

import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

import jakarta.persistence.*;
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
    private LocalDate dateNaissance;

    @Column(length = 50)
    private String lieuNaissance;

    @Column(length = 50)
    private String matricule;

    @Column(length = 50)
    private String anneeAcademique;

    @ManyToOne // Relation avec la faculté
    @JoinColumn(name = "faculte_id", nullable = false)
    private Facultes faculte;

    @ManyToOne // Relation avec le département
    @JoinColumn(name = "departement_id", nullable = false)
    private Departements departement;

    @ManyToOne // Relation avec la filière
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filieres filiere;

    @Column(length = 50)
    private String motpass;

    @Column(length = 150)
    private String role;

    @ManyToOne
    @JoinColumn(name = "niveaux_id", nullable = false)
    private Niveaux niveau;

    private int payement;

    @Lob // Champ pour stocker la photo
    private byte[] photo;

    @Lob // Champ pour stocker la photo par défaut
    private byte[] photoDefaut;

    @Lob // Large Object pour stocker des données binaires
    private byte[] pdfAttestationScolarite;

    @Lob
    private byte[] pdfCarteEtudiant;

    // Constructeur avec les PDF et la photo
    public Etudiant(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String matricule,
                    String anneeAcademique, Facultes faculte, Departements departement, Filieres filiere,
                    String motpass, String role, Niveaux niveau, int payement, byte[] pdfAttestationScolarite,
                    byte[] pdfCarteEtudiant, byte[] photo, byte[] photoDefaut) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.matricule = matricule;
        this.anneeAcademique = anneeAcademique;
        this.faculte = faculte;
        this.departement = departement;
        this.filiere = filiere;
        this.motpass = motpass;
        this.role = (role == null || role.isEmpty()) ? "etudiant" : role;
        this.niveau = niveau;
        this.payement = payement;
        this.photo = photo;
        this.photoDefaut = photoDefaut;
        this.pdfAttestationScolarite = pdfAttestationScolarite;
        this.pdfCarteEtudiant = pdfCarteEtudiant;
    }

    public void setNiveau(Niveaux niveau) {
        this.niveau = niveau;
    }
}
