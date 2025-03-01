package com.gestion_etudiants.gestion_etudiants.Controller.Administrateur;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrateurController {
 
    @GetMapping("/administration")
    public String Administrateur(Model model){
        return "administration";
    }
}
