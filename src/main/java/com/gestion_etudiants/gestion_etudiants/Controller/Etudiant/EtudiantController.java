package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
// import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
// import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfCarteEtudiantService;
// import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService; // Corrigé la casse du nom de la classe

// import java.io.IOException;

// @Controller
// public class EtudiantController {

//     @Autowired
//     private EtudiantServiceImpl etudiantService;

//     @Autowired
//     private pdfService pdfService; // Corrigé la casse du nom de la classe

//     @Autowired
//     private pdfCarteEtudiantService pdfCarteEtudiantService;

//     @PostMapping("/enregistrer")
// public ResponseEntity<byte[]> enregistrerEtudiant(@RequestParam String nom,
//                                                    @RequestParam String prenom,
//                                                    @RequestParam String dateNaissance,
//                                                    @RequestParam String lieuNaissance,
//                                                    @RequestParam String matricule,
//                                                    @RequestParam String anneeAcademique,
//                                                    @RequestParam String faculte,
//                                                    @RequestParam String departement,
//                                                    @RequestParam String filiere,
//                                                    @RequestParam int niveau,
//                                                    @RequestParam int payement,
//                                                    @RequestParam("photo") MultipartFile photo,
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
//     etudiant.setNiveau(niveau);
//     etudiant.setPayement(payement);

//     // Stockez la photo dans le champ photo
//     try {
//         etudiant.setPhoto(photo.getBytes());
//     } catch (IOException e) {
//         System.err.println("Erreur lors du chargement de la photo : " + e.getMessage());
//     }

//     // Générez le PDF
//     byte[] pdfBytes = pdfService.generatePdf(etudiant);

//     byte[] pdfCarteEtudiant = pdfCarteEtudiantService.generateMembershipCard(etudiant);

//     // Stockez le PDF dans le champ pdfAttestationScolarite
//     etudiant.setPdfAttestationScolarite(pdfBytes);

//     etudiant.setPdfCarteEtudiant(pdfCarteEtudiant);

//     // Enregistrez l'étudiant dans la base de données
//     Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);

//     // Configurez la réponse HTTP pour télécharger le PDF
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.APPLICATION_PDF);
    // headers.setContentDispositionFormData("filename", "etudiant_" + savedEtudiant.getNom() + "_" + savedEtudiant.getPrenom() + ".pdf");

    // // Retournez le PDF en tant que réponse HTTP
    // return ResponseEntity.ok()
    //         .headers(headers)
    //         .body(savedEtudiant.getPdfAttestationScolarite());
// }

// }


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfCarteEtudiantService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.List;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private pdfService pdfService;

    @Autowired
    private pdfCarteEtudiantService pdfCarteEtudiantService;

    @PostMapping("/enregistrer")
    public ResponseEntity<byte[]> enregistrerEtudiant(@RequestParam String nom,
                                                       @RequestParam String prenom,
                                                       @RequestParam String dateNaissance,
                                                       @RequestParam String lieuNaissance,
                                                       @RequestParam String matricule,
                                                       @RequestParam String anneeAcademique,
                                                       @RequestParam String faculte,
                                                       @RequestParam String departement,
                                                       @RequestParam String filiere,
                                                       @RequestParam int niveau,
                                                       @RequestParam int payement,
                                                       @RequestParam("photo") MultipartFile photo,
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
        etudiant.setNiveau(niveau);
        etudiant.setPayement(payement);

        // Stockez la photo dans le champ photo
        try {
            etudiant.setPhoto(photo.getBytes());
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la photo : " + e.getMessage());
        }

        // Générez les PDF
        byte[] pdfAttestationScolarite = pdfService.generatePdf(etudiant);
        byte[] pdfCarteEtudiant = pdfCarteEtudiantService.generateMembershipCard(etudiant);
    
        // Stockez les PDF dans les champs correspondants
        etudiant.setPdfAttestationScolarite(pdfAttestationScolarite);
        etudiant.setPdfCarteEtudiant(pdfCarteEtudiant);
    
        // Enregistrez l'étudiant dans la base de données
        Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);

        HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("filename", "etudiant_" + savedEtudiant.getNom() + "_" + savedEtudiant.getPrenom() + ".pdf");

    // // Retournez le PDF en tant que réponse HTTP
    return ResponseEntity.ok()
            .headers(headers)
            .body(savedEtudiant.getPdfAttestationScolarite());
    
        // Créer un fichier ZIP
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // try (ZipOutputStream zos = new ZipOutputStream(baos)) {
        //     // Ajouter l'attestation de scolarité
        //     ZipEntry entry1 = new ZipEntry("attestation_scolarite.pdf");
        //     zos.putNextEntry(entry1);
        //     zos.write(pdfAttestationScolarite);
        //     zos.closeEntry();

        //     // Ajouter la carte d'étudiant
        //     ZipEntry entry2 = new ZipEntry("carte_etudiant.pdf");
        //     zos.putNextEntry(entry2);
        //     zos.write(pdfCarteEtudiant);
        //     zos.closeEntry();
        // }

        // // Configurer la réponse HTTP pour le ZIP
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // headers.setContentDispositionFormData("filename", "documents.zip");

        // return ResponseEntity.ok()
        //         .headers(headers)
        //         .body(baos.toByteArray());
    }

}
