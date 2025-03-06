package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.gestion_etudiants.gestion_etudiants.Service.Administrateur.UserServiceImplement;
import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Administrateur.User;
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

    @Autowired
    private UserServiceImplement userServiceImplement;

    // @Autowired
    // private User user;

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
                                //    @RequestParam("default_logo") MultipartFile photoDefaut,
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

    // if (!photoDefaut.isEmpty()) {
    //     etudiant.setPhotoDefaut(photoDefaut.getBytes());
    // }

    // Récupérer le premier utilisateur
    User user = userServiceImplement.getFirstUser();
    if (user == null) {
        model.addAttribute("error", "Aucun utilisateur trouvé.");
        return "error"; // Redirigez vers une page d'erreur
    }

    // Générez les PDF
    byte[] pdfAttestationScolarite = pdfService.generatePdf(etudiant, user);
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

@GetMapping("/etudiants/{matricule}/pdfs")
public ResponseEntity<byte[]> getEtudiantPDFs(@PathVariable String matricule) throws IOException {
    byte[] pdf = etudiantService.getEtudiantPDFByMatricule(matricule);
    if (pdf == null) {
        return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("inline", "attestation_scolarite.pdf");  // Change 'attachment' to 'inline'

    return ResponseEntity.ok().headers(headers).body(pdf);
}

@GetMapping("/etudiants-carte/{matricule}/pdfs")
    public ResponseEntity<byte[]> getEtudiantPDFByCarteEtudiant(@PathVariable String matricule) throws IOException {
        byte[] pdf = etudiantService.getEtudiantPDFByCarteEtudiant(matricule);
        if (pdf == null) {
            return ResponseEntity.notFound().build();
        }
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "carte_etudiant.pdf");
    
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}