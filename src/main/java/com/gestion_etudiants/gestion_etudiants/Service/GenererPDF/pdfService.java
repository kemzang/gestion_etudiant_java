package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

// import com.gestion_etudiants.gestion_etudiants.Repository.EtudiantRepository;
// import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

// // public class pdfService {
    
// // }


// import com.itextpdf.io.font.constants.StandardFonts;
// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.element.Paragraph;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.io.ByteArrayOutputStream;
// import java.util.List;

// @Service
// public class pdfService {

//     @Autowired
//     private EtudiantRepository etudiantRepository;

//     public ByteArrayOutputStream generatePdf() {
//         List<Etudiant> etudiants = etudiantRepository.findAll();
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
//         try {
//             PdfWriter writer = new PdfWriter(baos);
//             PdfDocument pdf = new PdfDocument(writer);
//             Document document = new Document(pdf);

//             document.add(new Paragraph("Liste des Étudiants").setFontSize(20).setFont(StandardFonts.HELVETICA_BOLD));

//             for (Etudiant etudiant : etudiants) {
//                 document.add(new Paragraph(etudiant.getNom() + " " + etudiant.getPrenom() + ", "
//                         + etudiant.getFaculte() + ", "
//                         + etudiant.getDepartement() + ", "
//                         + etudiant.getFiliere() + ", "
//                         + etudiant.getNiveau()));
//             }

//             document.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
        
//         return baos;
//     }
// }

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


@Service
public class pdfService {

    public byte[] generatePdf(Etudiant etudiant) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Ajout du contenu
            document.add(new Paragraph("Informations de l'étudiant")
                    .setFontSize(18)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));
            document.add(new Paragraph("Nom : " + etudiant.getNom()));
            document.add(new Paragraph("Prénom : " + etudiant.getPrenom()));
            document.add(new Paragraph("Faculté : " + etudiant.getFaculte()));
            document.add(new Paragraph("Département : " + etudiant.getDepartement()));
            document.add(new Paragraph("Filière : " + etudiant.getFiliere()));
            document.add(new Paragraph("Niveau : " + etudiant.getNiveau()));

            // Fermer le document pour s'assurer que tout est écrit dans le flux
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        }
    }
}
