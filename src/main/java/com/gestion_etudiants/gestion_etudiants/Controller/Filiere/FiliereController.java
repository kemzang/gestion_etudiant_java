package com.gestion_etudiants.gestion_etudiants.Controller.Filiere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;

import com.gestion_etudiants.gestion_etudiants.Service.Filiere.FiliereServiceImplement;

@Controller
public class FiliereController {
    
    @Autowired
    private FiliereServiceImplement filiereServiceImplement;

    public List<Filieres> listFiliere() {
        return filiereServiceImplement.ListFiliere();
    }
}
