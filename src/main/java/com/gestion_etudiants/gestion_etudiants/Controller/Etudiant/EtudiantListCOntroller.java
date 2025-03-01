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
    // public ResponseEntity<byte[]> getEtudiantPDFs(@PathVariable String matricule) throws IOException {
    //     byte[][] pdfs = etudiantServiceImpl.getEtudiantPDFsByMatricule(matricule);
    //     if (pdfs == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    //     }

    //     ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //     ZipOutputStream zos = new ZipOutputStream(bos);
    //     zos.putNextEntry(new ZipEntry("attestation_scolarite.pdf"));
    //     zos.write(pdfs[0]);
    //     zos.closeEntry();
    //     zos.putNextEntry(new ZipEntry("carte_etudiant.pdf"));
    //     zos.write(pdfs[1]);
    //     zos.closeEntry();
    //     zos.close();

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    //     headers.setContentDispositionFormData("attachment", "documents.zip");

    //     return ResponseEntity.ok().headers(headers).body(bos.toByteArray());
    // }

    public ResponseEntity<byte[]> getEtudiantPDFs(@PathVariable String matricule) throws IOException {
        byte[] pdf = etudiantServiceImpl.getEtudiantPDFByMatricule(matricule);
        if (pdf == null) {
            return ResponseEntity.notFound().build();
        }
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "attestation_scolarite.pdf");
    
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
