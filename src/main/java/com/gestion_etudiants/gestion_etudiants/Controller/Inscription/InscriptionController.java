// package com.gestion_etudiants.gestion_etudiants.Controller.Inscription;

// import com.gestion_etudiants.gestion_etudiants.Controller.Departement.DepartementController;
// import com.gestion_etudiants.gestion_etudiants.Controller.Etudiant.EtudiantController;
// import com.gestion_etudiants.gestion_etudiants.Controller.Faculte.FaculteController;
// import com.gestion_etudiants.gestion_etudiants.Controller.Filiere.FiliereController;
// import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
// // import com.gestion_etudiants.gestion_etudiants.Service.User.UserServiceImplement;
// import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
// import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
// import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
// import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
// // import com.gestion_etudiants.gestion_etudiants.models.Users.User;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;


// @Controller
// public class InscriptionController {

//     @Autowired
//     private FaculteController faculteService; // Injection du service des facultés

//     @Autowired
//     private DepartementController departementService; // Injection du service des départements

//     @Autowired
//     private FiliereController filiereService;

//     @Autowired
//     private EtudiantController etudiantService;

//     // private UserServiceImplement userService; // Assurez-vous que le service est annoté avec @Autowired

//     @GetMapping("/inscription")
//     public String Inscription(Model model) {
//         List<Facultes> facultes = faculteService.listFaculte(); // Récupère toutes les facultés
//         List<Departements> departements = departementService.listDepartement(); // Utilise le service pour récupérer les départements
//         List<Filieres> filieres = filiereService.listFiliere();
//         // List<Etudiant> etudiants = etudiantService.listEtudiants();

//         // model.addAttribute("etudiants", etudiants);

//         model.addAttribute("facultes", facultes); // Ajoute la liste au modèle
//         model.addAttribute("departements", departements);
//         model.addAttribute("filieres", filieres);
//         model.addAttribute("message", "Bienvenue sur la page d'accueil");
//         // model.addAttribute("user", new Etudiant());
//         return "inscription";
//     }

//     @PostMapping("/inscription")
//     public String registerUser(@ModelAttribute Etudiant user, Model model) {
//         userService.registerUser(user); // Cette ligne peut causer NullPointerException si userService est null
//         model.addAttribute("successMessage", "Inscription réussie !");
//         return "connexion"; // Redirige vers la page de connexion ou une autre page
//     }
// }

package com.gestion_etudiants.gestion_etudiants.Controller.Inscription;

import com.gestion_etudiants.gestion_etudiants.Controller.Departement.DepartementController;
import com.gestion_etudiants.gestion_etudiants.Controller.Etudiant.EtudiantController;
import com.gestion_etudiants.gestion_etudiants.Controller.Faculte.FaculteController;
import com.gestion_etudiants.gestion_etudiants.Controller.Filiere.FiliereController;
import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

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
    private FaculteController faculteService; // Injection du service des facultés

    @Autowired
    private DepartementController departementService; // Injection du service des départements

    @Autowired
    private FiliereController filiereService; // Injection du service des filières

    @Autowired
    private EtudiantServiceImpl etudiantService; // Injection du service des étudiants

    @GetMapping("/inscription")
    public String showInscriptionForm(Model model) {
        List<Facultes> facultes = faculteService.listFaculte(); // Récupère toutes les facultés
        List<Departements> departements = departementService.listDepartement(); // Utilise le service pour récupérer les départements
        List<Filieres> filieres = filiereService.listFiliere(); // Récupère toutes les filières

        model.addAttribute("facultes", facultes); // Ajoute la liste au modèle
        model.addAttribute("departements", departements);
        model.addAttribute("filieres", filieres);
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