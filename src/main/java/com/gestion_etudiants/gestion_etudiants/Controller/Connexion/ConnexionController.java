package com.gestion_etudiants.gestion_etudiants.Controller.Connexion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// import com.gestion_etudiants.gestion_etudiants.Service.User.UserServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
// import com.gestion_etudiants.gestion_etudiants.models.Users.User;


import org.springframework.ui.Model;

@Controller
public class ConnexionController {

    // @Autowired
    // private EtudiantServiceImpl userService;
    // // private UserServiceImplement userService;

    @GetMapping("/connexion")
    public String Connexion(Model model){
        model.addAttribute("user", new Etudiant());
        return "connexion";
    }

    // @PostMapping("/session_etudiant")
// public String loginUser(@ModelAttribute Etudiant user, HttpSession session, Model model) {
//     Etudiant existingUser = userService.findByUsernameAndPasswordAndRole(user.getMatricule(), user.getMotpass(), user.getRole());

//     if (existingUser != null && user.getRole().equals("etudiant")) {
//         session.setAttribute("user", existingUser); // Démarre une nouvelle session
//         return "redirect:/index"; // Redirige vers index.html
//     } else if (existingUser != null && user.getRole().equals("administrateur")) {
//         session.setAttribute("user", existingUser); // Démarre une nouvelle session
//         return "redirect:/administration"; // Redirige vers la page d'administration
//     } else {
//         model.addAttribute("errorMessage", "Nom d'utilisateur, mot de passe ou rôle incorrect.");
//         return "session_etudiant"; // Renvoie à la page de connexion avec un message d'erreur
//     }
// }

// public String loginUser(@ModelAttribute Etudiant user, HttpSession session, Model model) {
//     Etudiant existingUser = userService.findByUsernameAndPasswordAndRole(user.getMatricule(), user.getMotpass(), user.getRole());

//     if (existingUser != null && user.getRole().equals("etudiant")) {
//         session.setAttribute("user", existingUser); // Démarre une nouvelle session
//         model.addAttribute("nom", existingUser.getNom());
//         model.addAttribute("prenom", existingUser.getPrenom());
//         return "session_etudiant"; // Renvoie à la page session_etudiant
//     } else if (existingUser != null && user.getRole().equals("administrateur")) {
//         session.setAttribute("user", existingUser); // Démarre une nouvelle session
//         return "redirect:/administration"; // Redirige vers la page d'administration
//     } else {
//         model.addAttribute("errorMessage", "Nom d'utilisateur, mot de passe ou rôle incorrect.");
//         return "session_etudiant"; // Renvoie à la page de connexion avec un message d'erreur
//     }
// }
}
