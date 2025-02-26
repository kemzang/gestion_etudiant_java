package com.gestion_etudiants.gestion_etudiants.Service.GenererPDF;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Div;
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
            Paragraph title = new Paragraph("CARTES DE MEMBRE")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold();
            document.add(title);

            // Créer un Div pour la mise en page
            Div mainDiv = new Div();
            mainDiv.setMarginTop(20); // Espace au-dessus

            // Ajouter l'image de l'étudiant
            byte[] imageBytes = etudiant.getPhoto(); // Assurez-vous que cette méthode retourne un tableau d'octets
            if (imageBytes != null) {
                Image image = new Image(ImageDataFactory.create(imageBytes));
                image.setWidth(UnitValue.createPercentValue(30)); // Ajuster la largeur de l'image
                mainDiv.add(image);
            } else {
                System.err.println("Erreur : l'image de l'étudiant est nulle.");
            }

            // Créer un Div pour les informations
            Div infoDiv = new Div();
            infoDiv.setMarginLeft(20); // Espace à gauche

            // Ajouter les informations de l'étudiant
            infoDiv.add(new Paragraph("NOM & PRÉNOMS : " + etudiant.getNom() + " " + etudiant.getPrenom()).setBold());
            infoDiv.add(new Paragraph("MATRICULE : " + etudiant.getMatricule()).setBold());
            infoDiv.add(new Paragraph("QUALITÉ : MEMBRE").setBold());
            infoDiv.add(new Paragraph("FILIERE & NIVEAU : " + etudiant.getFiliere() + " " + etudiant.getNiveau()).setBold());
            infoDiv.add(new Paragraph("ANNÉE ACADÉMIQUE : " + etudiant.getAnneeAcademique()).setBold());

            // Ajouter les informations au Div principal
            mainDiv.add(infoDiv);
            document.add(mainDiv);

            // Ajouter un footer
            document.add(new Paragraph("Association des étudiants de la faculté des Sciences")
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