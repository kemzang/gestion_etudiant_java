package com.gestion_etudiants.gestion_etudiants.Controller.Departement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gestion_etudiants.gestion_etudiants.Service.Departement.DepartementServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;

@Controller
public class DepartementController {

    @Autowired
    private DepartementServiceImplement departementServiceImplement;

    
    public List<Departements> listDepartement() {
        return departementServiceImplement.ListDepartement();
    }
}