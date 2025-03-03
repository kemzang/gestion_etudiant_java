package com.gestion_etudiants.gestion_etudiants.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccueilController {

  

    @GetMapping("/index")
    public String accueil(Model model) {
        // List<Facultes> facultes = faculteService.listFaculte(); // Récupère toutes les facultés
        // List<Departements> departements = departementService.listDepartement(); // Utilise le service pour récupérer les départements
        // List<Filieres> filieres = filiereService.listFiliere();
        // List<Etudiant> etudiants = etudiantService.listEtudiants();

        // model.addAttribute("etudiants", etudiants);

        // model.addAttribute("facultes", facultes); // Ajoute la liste au modèle
        // model.addAttribute("departements", departements);
        // model.addAttribute("filieres", filieres);
        // model.addAttribute("message", "Bienvenue sur la page d'accueil");
        return "index"; // Retourne le nom de la vue
    }
}