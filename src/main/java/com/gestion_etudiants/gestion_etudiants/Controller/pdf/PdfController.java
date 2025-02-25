package com.gestion_etudiants.gestion_etudiants.Controller.pdf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_etudiants.gestion_etudiants.Repository.EtudiantRepository;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService; // Corrigé le nom de la classe

@RestController
public class PdfController {

    private final pdfService pdfService; // Corrigé le nom de la classe
    private final EtudiantRepository etudiantRepository;

    @Autowired
    public PdfController(pdfService pdfService, EtudiantRepository etudiantRepository) {
        this.pdfService = pdfService;
        this.etudiantRepository = etudiantRepository;
    }

    // @GetMapping("/generatePdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id) throws java.io.IOException {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdfBytes = pdfService.generatePdf(etudiant);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "etudiant_" + etudiant.getNom() + "_" + etudiant.getPrenom() + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
