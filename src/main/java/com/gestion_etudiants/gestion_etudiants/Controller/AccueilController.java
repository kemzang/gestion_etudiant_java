package com.gestion_etudiants.gestion_etudiants.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gestion_etudiants.gestion_etudiants.Controller.Departement.DepartementController;
import com.gestion_etudiants.gestion_etudiants.Controller.Faculte.FaculteController;
import com.gestion_etudiants.gestion_etudiants.Controller.Filiere.FiliereController;
import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

import java.util.List;

@Controller
public class AccueilController {

    @Autowired
    private FaculteController faculteService; // Injection du service des facultés

    @Autowired
    private DepartementController departementService; // Injection du service des départements

    @Autowired
    private FiliereController filiereService;

    @Autowired
    private EtudiantServiceImpl etudiantService;

    @GetMapping("/index")
    public String accueil(Model model) {
        List<Facultes> facultes = faculteService.listFaculte(); // Récupère toutes les facultés
        List<Departements> departements = departementService.listDepartement(); // Utilise le service pour récupérer les départements
        List<Filieres> filieres = filiereService.listFiliere();
        List<Etudiant> etudiants = etudiantService.listEtudiants();

        model.addAttribute("etudiants", etudiants);

        model.addAttribute("facultes", facultes); // Ajoute la liste au modèle
        model.addAttribute("departements", departements);
        model.addAttribute("filieres", filieres);
        model.addAttribute("message", "Bienvenue sur la page d'accueil");
        return "index"; // Retourne le nom de la vue
    }
}