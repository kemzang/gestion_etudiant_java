package com.gestion_etudiants.gestion_etudiants.Controller.Filiere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion_etudiants.gestion_etudiants.models.Departement.Departements;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.Service.Departement.DepartementService;
import com.gestion_etudiants.gestion_etudiants.Service.Filiere.FiliereServiceImplement;

@Controller
public class FiliereController {
    
    @Autowired
    private FiliereServiceImplement filiereServiceImplement;

    @Autowired
    private DepartementService departementService;

    @GetMapping("/filieres")
    public String showFilierePage(Model model) {
        List<Departements> departements = departementService.findAll(); // Récupérer tous les départements
        model.addAttribute("departements", departements);
        return "filiere"; // Nom du template
    }

    @GetMapping("/filieres/departement/{id}")
    @ResponseBody
    public List<Filieres> getFilieresByDepartement(@PathVariable Long id) {
        List<Filieres> filieres = filiereServiceImplement.findByDepartementId(id); 
        if (filieres.isEmpty()) {
            // Gérer le cas où aucune filière n'est trouvée, par exemple, retourner une liste vide ou un message
        }
        return filieres; // Récupérer les filières par ID de département
    }

    @GetMapping("/filieres/list")
    @ResponseBody
    public List<Filieres> listFiliere() {
        return filiereServiceImplement.ListFiliere(); // Renommer en camelCase si nécessaire
    }

     @PutMapping("/filiere/modifier/{id}")
    public ResponseEntity<Filieres> modifierFiliere(@PathVariable Long id, @RequestBody Filieres filiere) {
        Filieres filiereModifiee = filiereServiceImplement.modifierFiliere(id, filiere);
        return ResponseEntity.ok(filiereModifiee);
    }

    @DeleteMapping("/filiere/supprimer/{id}")
    public ResponseEntity<Void> supprimerFiliere(@PathVariable Long id) {
        filiereServiceImplement.supprimerFiliere(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/filiere/ajouter")
    public ResponseEntity<Filieres> ajouterFiliere(@RequestBody Filieres filiere) {
        Filieres nouvelleFiliere = filiereServiceImplement.ajouterFiliere(filiere);
        return ResponseEntity.status(201).body(nouvelleFiliere);
    }
}