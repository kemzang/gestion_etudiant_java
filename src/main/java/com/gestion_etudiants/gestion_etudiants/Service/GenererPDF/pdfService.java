package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.itextpdf.io.image.ImageDataFactory;

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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;


@Service
public class pdfService {

    public byte[] generatePdf(Etudiant etudiant) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("REPUBLIQUE DU CAMEROUN")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Paix -- Patrie")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("UNIVERSITÉ DE YAOUNDÉ I")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("FACULTE DES SCIENCES B.P. 812 Yaoundé,")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Tél/Fax. : (237) 22.23.44.96")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
                    try {
                        String imagePath = getClass().getClassLoader().getResource("images/img.jpg").getPath();
                        Image image = new Image(ImageDataFactory.create(imagePath));
                        document.add(image);
                    } catch (Exception e) {
                        System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                        document.add(new Paragraph("Image non disponible").setFontSize(12).setTextAlignment(TextAlignment.CENTER));
                    }
                    
            document.add(new Paragraph("Vice-Doyen chargé de la Scolarité ct du Suivi des Etudiants")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
                    document.add(new Paragraph("REPUBLIC OF CAMEROON")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Peace — Work — Fatherland")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("UNIVERSITY OF YAOUNDE I")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("FACULTY OF SCIENCE")
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("P.O.Box 812 Yaounde,")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Tel/Fax. : (237) 22.23.44.96")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Vice-Dean in charge of Students Affairs")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));
                    document.add(new Paragraph("NO UFS/VDSSE/24")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));

            // Date
            document.add(new Paragraph("Yaoundé, le [Date]")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));

            // Titre de l'attestation
            document.add(new Paragraph("ATTESTATION D'INSCRIPTION")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            // Contenu de l'attestation
            document.add(new Paragraph("Le Doyen de la Faculté des Sciences atteste que, Monsieur " + etudiant.getNom() + " " + etudiant.getPrenom() + ", né le "+ etudiant.getDateNaissance()+" à "+ etudiant.getLieuNaissance() + "de  matricule "+ etudiant.getMatricule()  + ", a été étudiant régulièrement inscrit en " + etudiant.getNiveau() + ", filière " + etudiant.getFiliere() + ", pour le compte de l'année académique " + etudiant.getAnneeAcademique() + ", à la Faculté des Sciences de l'Université de Yaoundé I.")
                    .setFontSize(12));
            document.add(new Paragraph("En foi de quoi la présente Attestation lui est délivrée pour servir et valoir ce que de droit.")
                    .setFontSize(12));
            document.add(new Paragraph("Le Vice-Doyen chargé de la Scolarité et du Suivi des Etudiants")
                    .setFontSize(12));

            // Fermer le document pour s'assurer que tout est écrit dans le flux
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        }
    }
}