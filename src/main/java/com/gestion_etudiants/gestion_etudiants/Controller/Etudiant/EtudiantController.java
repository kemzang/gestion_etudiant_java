package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfCarteEtudiantService;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private pdfService pdfService;

    @Autowired
    private pdfCarteEtudiantService pdfCarteEtudiantService;

//     @PostMapping("/enregistrer")
// // public ResponseEntity<String> enregistrerEtudiant(@RequestParam String nom,
// public String enregistrerEtudiant(@RequestParam String nom,
//                                                    @RequestParam String prenom,
//                                                    @RequestParam String dateNaissance,
//                                                    @RequestParam String lieuNaissance,
//                                                    @RequestParam String matricule,
//                                                    @RequestParam String anneeAcademique,
//                                                    @RequestParam Facultes faculte,
//                                                    @RequestParam Departements departement,
//                                                    @RequestParam Filieres filiere,
//                                                    @RequestParam String motpass,
//                                                    @RequestParam Niveaux niveau,
//                                                    @RequestParam int payement,
//                                                    @RequestParam("photo") MultipartFile photo,
//                                                    @RequestParam("default_logo") MultipartFile photoDefaut,
//                                                    Model model) throws IOException {
//     Etudiant etudiant = new Etudiant();
//     etudiant.setNom(nom);
//     etudiant.setPrenom(prenom);
//     etudiant.setDateNaissance(dateNaissance);
//     etudiant.setLieuNaissance(lieuNaissance);
//     etudiant.setMatricule(matricule);
//     etudiant.setAnneeAcademique(anneeAcademique);
//     etudiant.setFaculte(faculte);
//     etudiant.setDepartement(departement);
//     etudiant.setFiliere(filiere);
//     etudiant.setMotpass(motpass);
//     etudiant.setNiveau(niveau);
//     etudiant.setPayement(payement);

//     // Stockez la photo dans le champ photo
//     try {
//         etudiant.setPhoto(photo.getBytes());
//     } catch (IOException e) {
//         System.err.println("Erreur lors du chargement de la photo : " + e.getMessage());
//     }

//     try {
//         etudiant.setPhotoDefaut(photoDefaut.getBytes());
//     } catch (IOException e) {
//         System.err.println("Erreur lors du chargement du logo : " + e.getMessage());
//     }


//     // etudiant.setPhotoDefaut(defaultImage);

//     // Générez les PDF
//     byte[] pdfAttestationScolarite = pdfService.generatePdf(etudiant);
//     byte[] pdfCarteEtudiant = pdfCarteEtudiantService.generateMembershipCard(etudiant);

//     // Stockez les PDF dans les champs correspondants
//     etudiant.setPdfAttestationScolarite(pdfAttestationScolarite);
//     etudiant.setPdfCarteEtudiant(pdfCarteEtudiant);

//     // Enregistrez l'étudiant dans la base de données
//     Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);

//     model.addAttribute("message", "Étudiant enregistré avec succès : " + savedEtudiant.getNom() + " " + savedEtudiant.getPrenom());

//     // Retournez la vue d'accueil
//     return "index";
// }

@PostMapping("/enregistrer")
public String enregistrerEtudiant(@RequestParam String nom,
                                   @RequestParam String prenom,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateNaissance,
                                   @RequestParam String lieuNaissance,
                                   @RequestParam String matricule,
                                   @RequestParam String anneeAcademique,
                                   @RequestParam Long faculteId,
                                   @RequestParam Long departementId,
                                   @RequestParam Long filiereId,
                                   @RequestParam String motpass,
                                   @RequestParam Long niveauId,
                                   @RequestParam int payement,
                                   @RequestParam("photo") MultipartFile photo,
                                   @RequestParam("default_logo") MultipartFile photoDefaut,
                                   Model model) throws IOException {

    // Récupérer les entités
    Facultes faculte = etudiantService.findFaculteById(faculteId);
    Departements departement = etudiantService.findDepartementById(departementId);
    Filieres filiere = etudiantService.findFiliereById(filiereId);
    Niveaux niveau = etudiantService.findNiveauById(niveauId);

    // Vérifiez que le niveau est bien récupéré
    if (niveau == null) {
        model.addAttribute("error", "Niveau introuvable.");
        return "error"; // Redirigez vers une page d'erreur
    }

    // Créer un nouvel étudiant
    Etudiant etudiant = new Etudiant();
    etudiant.setNom(nom);
    etudiant.setPrenom(prenom);
    etudiant.setDateNaissance(dateNaissance);
    etudiant.setLieuNaissance(lieuNaissance);
    etudiant.setMatricule(matricule);
    etudiant.setAnneeAcademique(anneeAcademique);
    etudiant.setFaculte(faculte);
    etudiant.setDepartement(departement);
    etudiant.setFiliere(filiere);
    etudiant.setMotpass(motpass);
    etudiant.setNiveau(niveau); // Assurez-vous d'assigner le niveau
    etudiant.setPayement(payement);

    // Vérifiez et stockez la photo
    if (!photo.isEmpty()) {
        etudiant.setPhoto(photo.getBytes());
    } else {
        model.addAttribute("error", "La photo est vide.");
        return "error"; // Redirigez vers une page d'erreur
    }

    if (!photoDefaut.isEmpty()) {
        etudiant.setPhotoDefaut(photoDefaut.getBytes());
    }

    // Générez les PDF
    byte[] pdfAttestationScolarite = pdfService.generatePdf(etudiant);
    byte[] pdfCarteEtudiant = pdfCarteEtudiantService.generateMembershipCard(etudiant);

    // Stockez les PDF dans les champs correspondants
    etudiant.setPdfAttestationScolarite(pdfAttestationScolarite);
    etudiant.setPdfCarteEtudiant(pdfCarteEtudiant);

    // Enregistrez l'étudiant dans la base de données
    Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);

    model.addAttribute("message", "Étudiant enregistré avec succès : " + savedEtudiant.getNom() + " " + savedEtudiant.getPrenom());

    // Retournez la vue d'accueil
    return "index";
}
}