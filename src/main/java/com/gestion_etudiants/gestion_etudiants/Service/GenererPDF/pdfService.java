package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.element.Table;

@Service
public class pdfService {

    public byte[] generatePdf(Etudiant etudiant) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Création d'une table à 3 colonnes sans bordures
            Table table = new Table(3);
            table.setWidth(UnitValue.createPercentValue(100));
            table.setBorder(Border.NO_BORDER); // Retirer les bordures de la table
            
            // Colonne de gauche avec le texte
            String leftText = "REPUBLIQUE DU CAMEROUN\n" +
                              "Paix -- Patrie\n" +
                              "UNIVERSITÉ DE YAOUNDÉ I\n" +
                              "FACULTE DES SCIENCES B.P. 812 Yaoundé,\n" +
                              "Tél/Fax. : (237) 22.23.44.96";
            table.addCell(new Paragraph(leftText)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER)); // Retirer la bordure de la cellule

            // Image au centre
            byte[] photoDefaut = etudiant.getPhotoDefaut(); 
            Image image;
            if (photoDefaut != null) {
                image = new Image(ImageDataFactory.create(photoDefaut))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setAutoScale(true); // Ajuste l'image à la cellule
            } else {
                image = new Image(ImageDataFactory.create("path/to/default/image.jpg")); // Remplacez par une image par défaut
            }
            table.addCell(image.setBorder(Border.NO_BORDER)); // Ajout de l'image au centre sans bordure

            // Colonne de droite avec le texte
            String rightText = "Vice-Doyen chargé de la Scolarité ct du Suivi des Etudiants\n" +
                               "REPUBLIC OF CAMEROON\n" +
                               "Peace — Work — Fatherland\n" +
                               "UNIVERSITY OF YAOUNDE I\n" +
                               "FACULTY OF SCIENCE\n" +
                               "P.O.Box 812 Yaounde,\n" +
                               "Tel/Fax. : (237) 22.23.44.96\n" +
                               "Vice-Dean in charge of Students Affairs\n" +
                               "NO UFS/VDSSE/24";
            table.addCell(new Paragraph(rightText)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setBorder(Border.NO_BORDER)); // Retirer la bordure de la cellule

            // Ajout de la table au document
            document.add(table);

            // Titre de l'attestation
            document.add(new Paragraph("ATTESTATION D'INSCRIPTION")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER));

            // Contenu de l'attestation
            document.add(new Paragraph("Le Doyen de la Faculté des Sciences atteste que, Monsieur " + etudiant.getNom() + " " + etudiant.getPrenom() + ", né le "+ etudiant.getDateNaissance()+" à "+ etudiant.getLieuNaissance() + ", de matricule "+ etudiant.getMatricule() + ", a été étudiant régulièrement inscrit en " + etudiant.getNiveau() + ", filière " + etudiant.getFiliere() + ", pour le compte de l'année académique " + etudiant.getAnneeAcademique() + ", à la Faculté des Sciences de l'Université de Yaoundé I.")
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