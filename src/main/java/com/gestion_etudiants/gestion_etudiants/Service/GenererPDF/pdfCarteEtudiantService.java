package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.itextpdf.io.image.ImageDataFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class pdfCarteEtudiantService {

    public byte[] generateMembershipCard(Etudiant etudiant) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            // Définir les marges
            document.setMargins(20, 20, 20, 20);

            // Ajouter le titre
            Paragraph title = new Paragraph("CARTE ETUDIANT")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold();
            document.add(title);

            // Créer une table avec 2 colonnes
            Table table = new Table(2);
            table.setWidth(UnitValue.createPercentValue(100)); // 100% de la largeur de la page

            // Ajouter l'image de l'étudiant
            byte[] imageBytes = etudiant.getPhoto(); // Assurez-vous que cette méthode retourne un tableau d'octets
            if (imageBytes != null) {
                Image image = new Image(ImageDataFactory.create(imageBytes));
                image.setWidth(UnitValue.createPercentValue(30)); // Ajuster la largeur de l'image
                table.addCell(image); // Image dans la première colonne
            } else {
                System.err.println("Erreur : l'image de l'étudiant est nulle.");
                table.addCell(new Paragraph("Image non disponible").setTextAlignment(TextAlignment.CENTER));
            }

            // Créer un Div pour les informations
            StringBuilder infoText = new StringBuilder();
            infoText.append("NOM & PRÉNOMS : ").append(etudiant.getNom()).append(" ").append(etudiant.getPrenom()).append("\n")
                    .append("MATRICULE : ").append(etudiant.getMatricule()).append("\n")
                    .append("QUALITÉ : ETUDIANT\n")
                    .append("FILIERE & NIVEAU : ").append(etudiant.getFiliere()).append(" ").append(etudiant.getNiveau()).append("\n")
                    .append("ANNÉE ACADÉMIQUE : ").append(etudiant.getAnneeAcademique()).append("\n");

            // Ajouter les informations de l'étudiant
            Paragraph infoParagraph = new Paragraph(infoText.toString()).setBold();
            table.addCell(infoParagraph); // Informations dans la deuxième colonne

            // Ajouter la table au document
            document.add(table);

            // Ajouter un footer
            document.add(new Paragraph("étudiants de la faculté des Sciences")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER));

            // Fermer le document pour s'assurer que tout est écrit
            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new IOException("Erreur lors de la génération de la carte de membre", e);
        }
    }
}