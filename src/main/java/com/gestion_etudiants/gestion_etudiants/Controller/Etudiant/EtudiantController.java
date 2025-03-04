package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfCarteEtudiantService;

import java.io.IOException;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private pdfService pdfService;

    @Autowired
    private pdfCarteEtudiantService pdfCarteEtudiantService;

    @PostMapping("/enregistrer")
// public ResponseEntity<String> enregistrerEtudiant(@RequestParam String nom,
public String enregistrerEtudiant(@RequestParam String nom,
                                                   @RequestParam String prenom,
                                                   @RequestParam String dateNaissance,
                                                   @RequestParam String lieuNaissance,
                                                   @RequestParam String matricule,
                                                   @RequestParam String anneeAcademique,
                                                   @RequestParam String faculte,
                                                   @RequestParam String departement,
                                                   @RequestParam String filiere,
                                                   @RequestParam String motpass,
                                                   @RequestParam int niveau,
                                                   @RequestParam int payement,
                                                   @RequestParam("photo") MultipartFile photo,
                                                   @RequestParam("default_logo") MultipartFile photoDefaut,
                                                   Model model) throws IOException {
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
    etudiant.setNiveau(niveau);
    etudiant.setPayement(payement);

    // Stockez la photo dans le champ photo
    try {
        etudiant.setPhoto(photo.getBytes());
    } catch (IOException e) {
        System.err.println("Erreur lors du chargement de la photo : " + e.getMessage());
    }

    try {
        etudiant.setPhotoDefaut(photoDefaut.getBytes());
    } catch (IOException e) {
        System.err.println("Erreur lors du chargement du logo : " + e.getMessage());
    }


    // etudiant.setPhotoDefaut(defaultImage);

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
