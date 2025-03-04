package com.gestion_etudiants.gestion_etudiants.Controller.Faculte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.gestion_etudiants.gestion_etudiants.Service.Faculte.FaculteServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

@Controller
public class FaculteController {

    @Autowired
    private FaculteServiceImplement faculteServiceImplement;

    @GetMapping("/faculte") // Endpoint pour récupérer la liste des facultés
    public String listFaculte(Model model) {
        List<Facultes> facultesList = faculteServiceImplement.ListFaculte(); // Appel à la méthode du service
        model.addAttribute("facultes", facultesList);
        return "faculte"; // Indique le nom de la vue
    }
}