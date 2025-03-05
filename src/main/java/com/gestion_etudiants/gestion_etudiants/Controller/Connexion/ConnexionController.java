package com.gestion_etudiants.gestion_etudiants.Controller.Connexion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;


import org.springframework.ui.Model;

@Controller
public class ConnexionController {

    @GetMapping("/connexion")
    public String Connexion(Model model){
        model.addAttribute("user", new Etudiant());
        return "connexion";
    }
}
