package com.gestion_etudiants.gestion_etudiants.Controller.Departement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion_etudiants.gestion_etudiants.Service.Departement.DepartementServiceImplement;
import com.gestion_etudiants.gestion_etudiants.Service.Faculte.FaculteServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

@Controller
public class DepartementController {

    @Autowired
    private FaculteServiceImplement faculteService;

    @Autowired
    private DepartementServiceImplement departementServiceImplement;

     @GetMapping("/departement")
    public String showDepartementPage(Model model) {
        List<Facultes> facultes = faculteService.ListFaculte(); // Récupération de toutes les facultés
        model.addAttribute("facultes", facultes);
        return "departement"; // Nom du template
    }

    @GetMapping("/departements/faculte/{id}")
    @ResponseBody
    public List<Departements> getDepartementsByFaculte(@PathVariable Long id) {
        return departementServiceImplement.findByFaculteId(id); // Méthode pour récupérer les départements par ID de faculté
    }

    
    public List<Departements> listDepartement() {
        return departementServiceImplement.ListDepartement();
    }
}