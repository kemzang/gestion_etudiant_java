package com.gestion_etudiants.gestion_etudiants.Controller.Inscription;

import com.gestion_etudiants.gestion_etudiants.Service.Faculte.FaculteServiceImplement; // Importer le service
import com.gestion_etudiants.gestion_etudiants.Service.Departement.DepartementService; // Service pour les départements
import com.gestion_etudiants.gestion_etudiants.Service.Filiere.FiliereService; // Service pour les filières
import com.gestion_etudiants.gestion_etudiants.Service.Niveaux.NiveauxServiceImplement;
import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl; // Service pour les étudiants
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscriptionController {

    @Autowired
    private FaculteServiceImplement faculteService; // Injection du service des facultés

    @Autowired
    private DepartementService departementService; // Injection du service des départements

    @Autowired
    private FiliereService filiereService; // Injection du service des filières

    @Autowired
    private EtudiantServiceImpl etudiantService; // Injection du service des étudiants

    @Autowired
    private NiveauxServiceImplement niveauxServiceImplement;

    @GetMapping("/inscription")
    public String showInscriptionForm(Model model) {
        List<Facultes> facultes = faculteService.ListFaculte(); // Récupère toutes les facultés
        List<Departements> departements = departementService.ListDepartement(); // Récupère tous les départements
        List<Filieres> filieres = filiereService.ListFiliere(); // Récupère toutes les filières
        List<Niveaux> niveauxs = niveauxServiceImplement.ListNiveaux();

        model.addAttribute("facultes", facultes); // Ajoute la liste au modèle
        model.addAttribute("departements", departements);
        model.addAttribute("filieres", filieres);
        model.addAttribute("niveaux", niveauxs);
        model.addAttribute("message", "Bienvenue sur la page d'inscription");
        model.addAttribute("etudiant", new Etudiant()); // Ajoute un nouvel objet Etudiant pour le formulaire

        return "inscription"; // Renvoie la vue d'inscription
    }

    @PostMapping("/inscription")
    public String registerUser(@ModelAttribute Etudiant etudiant, Model model) {
        etudiantService.createEtudiant(etudiant); // Crée un nouvel étudiant
        model.addAttribute("successMessage", "Inscription réussie !");
        return "connexion"; // Redirige vers la page de connexion ou une autre page
    }
}