package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;

@Controller
public class EtudiantListCOntroller {

    @Autowired
    private EtudiantServiceImpl etudiantServiceImpl;

    @GetMapping("/etudiants/{matricule}/pdfs")
public ResponseEntity<byte[]> getEtudiantPDFs(@PathVariable String matricule) throws IOException {
    byte[] pdf = etudiantServiceImpl.getEtudiantPDFByMatricule(matricule);
    if (pdf == null) {
        return ResponseEntity.notFound().build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    headers.setContentDispositionFormData("inline", "attestation_scolarite.pdf");  // Change 'attachment' to 'inline'

    return ResponseEntity.ok().headers(headers).body(pdf);
}
}
