// package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

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
