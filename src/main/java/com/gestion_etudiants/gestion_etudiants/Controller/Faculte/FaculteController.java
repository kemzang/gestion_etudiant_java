package com.gestion_etudiants.gestion_etudiants.Controller.Faculte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gestion_etudiants.gestion_etudiants.Service.Faculte.FaculteServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;

@Controller
public class FaculteController {

    @Autowired
    private FaculteServiceImplement faculteServiceImplement;

    // @GetMapping("/index") // Endpoint pour récupérer la liste des facultés
    public List<Facultes> listFaculte() {
        return faculteServiceImplement.ListFaculte(); // Appel à la méthode du service
    }
}