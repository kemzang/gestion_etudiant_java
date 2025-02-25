// package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

// import java.net.http.HttpHeaders;

// // import org.springframework.stereotype.Controller;

// // @Controller
// // public class EtudiantController {
    
// // }

// // package com.gestion_etudiants.gestion_etudiants.Controller;

// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
// import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;



// @Controller
// public class EtudiantController {

//     @Autowired
//     private EtudiantServiceImpl etudiantService;

//     @GetMapping("/enregistrer")
//     public String showForm(Model model) {
//         // Passer les données nécessaires à la vue
//         return "enregistrer"; // Le nom de la vue Thymeleaf
//     }

//     @PostMapping("/enregistrer")
//     public String enregistrerEtudiant(@RequestParam String nom,
//                                        @RequestParam String prenom,
//                                        @RequestParam String faculte,
//                                        @RequestParam String departement,
//                                        @RequestParam String filiere,
//                                        @RequestParam int niveau,
//                                        Model model) {
//         Etudiant etudiant = new Etudiant();
//         etudiant.setNom(nom);
//         etudiant.setPrenom(prenom);
//         etudiant.setFaculte(faculte);
//         etudiant.setDepartement(departement);
//         etudiant.setFiliere(filiere);
//         etudiant.setNiveau(niveau);

//         etudiantService.createEtudiant(etudiant); // Enregistre l'étudiant dans la base de données

//         model.addAttribute("message", "Étudiant enregistré avec succès !");
//         return "index"; // Redirige vers la page d'accueil ou une autre page
//     }



// }

// // package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpHeaders;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.stereotype.Controller;
// // import org.springframework.ui.Model;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.RequestParam;

// // import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
// // import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
// // import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService; // Corrigé l'import
// // import java.io.ByteArrayOutputStream; // Corrigé l'import

// // @Controller
// // public class EtudiantController {

// //     @Autowired
// //     private EtudiantServiceImpl etudiantService;

// //     @Autowired
// //     private pdfService pdfService; // Corrigé la casse du nom de la classe

// //     @GetMapping("/enregistrer")
// //     public String showForm(Model model) {
// //         return "enregistrer"; // Le nom de la vue Thymeleaf
// //     }

// //     @PostMapping("/enregistrer")
// //     public String enregistrerEtudiant(@RequestParam String nom,
// //                                        @RequestParam String prenom,
// //                                        @RequestParam String faculte,
// //                                        @RequestParam String departement,
// //                                        @RequestParam String filiere,
// //                                        @RequestParam int niveau,
// //                                        Model model) {
// //         Etudiant etudiant = new Etudiant();
// //         etudiant.setNom(nom);
// //         etudiant.setPrenom(prenom);
// //         etudiant.setFaculte(faculte);
// //         etudiant.setDepartement(departement);
// //         etudiant.setFiliere(filiere);
// //         etudiant.setNiveau(niveau);

// //         etudiantService.createEtudiant(etudiant); // Enregistre l'étudiant dans la base de données

// //         model.addAttribute("message", "Étudiant enregistré avec succès !");
// //         return "index"; // Redirige vers la page d'accueil ou une autre page
// //     }

// //     @GetMapping("/genererpdf")
// // // public ResponseEntity<byte[]> genererPdf() {
// // //     ByteArrayOutputStream pdfStream = pdfService.generatePdf();
// // //     byte[] pdfBytes = pdfStream.toByteArray();

// // //     HttpHeaders headers = new HttpHeaders();
// // //     headers.add("Content-Disposition", "inline; filename=etudiants.pdf");

// // //     return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
// // // }
// // }

package com.gestion_etudiants.gestion_etudiants.Controller.Etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.Service.GenererPDF.pdfService; // Corrigé la casse du nom de la classe

import java.io.IOException;

@Controller
public class EtudiantController {

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @Autowired
    private pdfService pdfService; // Corrigé la casse du nom de la classe

    @PostMapping("/enregistrer")
    public ResponseEntity<byte[]> enregistrerEtudiant(@RequestParam String nom,
                                                       @RequestParam String prenom,
                                                       @RequestParam String faculte,
                                                       @RequestParam String departement,
                                                       @RequestParam String filiere,
                                                       @RequestParam int niveau,
                                                       Model model) throws IOException {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setFaculte(faculte);
        etudiant.setDepartement(departement);
        etudiant.setFiliere(filiere);
        etudiant.setNiveau(niveau);

        // Enregistrez l'étudiant dans la base de données
        Etudiant savedEtudiant = etudiantService.createEtudiant(etudiant);

        // Générez le PDF
        byte[] pdfBytes = pdfService.generatePdf(savedEtudiant);

        // Configurez la réponse HTTP pour télécharger le PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "etudiant_" + savedEtudiant.getNom() + "_" + savedEtudiant.getPrenom() + ".pdf");

        // Retournez le PDF en tant que réponse HTTP
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
