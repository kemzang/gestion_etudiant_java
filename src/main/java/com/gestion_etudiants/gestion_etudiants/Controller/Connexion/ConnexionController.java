package com.gestion_etudiants.gestion_etudiants.Controller.Connexion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gestion_etudiants.gestion_etudiants.Service.User.UserServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Users.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class ConnexionController {

    @Autowired
    private UserServiceImplement userService;

    @GetMapping("/connexion")
    public String Connexion(Model model){
        model.addAttribute("user", new User());
        return "connexion";
    }

    @PostMapping("/connexion")
public String loginUser(@ModelAttribute User user, HttpSession session, Model model) {
    User existingUser = userService.findByUsernameAndPasswordAndRole(user.getUsername(), user.getPassword(), user.getRole());

    if (existingUser != null && user.getRole().equals("etudiant")) {
        session.setAttribute("user", existingUser); // Démarre une nouvelle session
        return "redirect:/index"; // Redirige vers index.html
    } else if (existingUser != null && user.getRole().equals("administrateur")) {
        session.setAttribute("user", existingUser); // Démarre une nouvelle session
        return "redirect:/administration"; // Redirige vers la page d'administration
    } else {
        model.addAttribute("errorMessage", "Nom d'utilisateur, mot de passe ou rôle incorrect.");
        return "connexion"; // Renvoie à la page de connexion avec un message d'erreur
    }
}
}
