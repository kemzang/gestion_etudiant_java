package com.gestion_etudiants.gestion_etudiants.Controller.Inscription;

import com.gestion_etudiants.gestion_etudiants.Service.User.UserServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class InscriptionController {

    @Autowired
    private UserServiceImplement userService; // Assurez-vous que le service est annoté avec @Autowired

    @GetMapping("/inscription")
    public String Inscription(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user); // Cette ligne peut causer NullPointerException si userService est null
        model.addAttribute("successMessage", "Inscription réussie !");
        return "connexion"; // Redirige vers la page de connexion ou une autre page
    }
}