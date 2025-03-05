package com.gestion_etudiants.gestion_etudiants.Controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccueilController {
    @GetMapping("/index")
    public String accueil(Model model) {
        return "index"; // Retourne le nom de la vue
    }
}