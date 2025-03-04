package com.gestion_etudiants.gestion_etudiants.Controller.Niveaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;
import java.util.List;

import com.gestion_etudiants.gestion_etudiants.Service.Filiere.FiliereServiceImplement;
import com.gestion_etudiants.gestion_etudiants.Service.Niveaux.NiveauxServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Filiere.Filieres;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

@Controller
public class NiveauxController {
    @Autowired
    private NiveauxServiceImplement niveauService;

    @Autowired
    private FiliereServiceImplement filiereServiceImplement;
    
    @GetMapping("/niveaux")
    public String listNiveaux(Model model) {
        List<Niveaux> niveaux = niveauService.ListNiveaux();
        List<Filieres> filieres = filiereServiceImplement.ListFiliere();
        model.addAttribute("filieres", filieres);
        model.addAttribute("niveaux", niveaux);
        return "niveaux"; // Nom de la vue à afficher
    }

    @GetMapping("/niveaux/filieres/{id}")
@ResponseBody
public ResponseEntity<List<Niveaux>> getFilieresByDepartement(@PathVariable Long id) {
    List<Niveaux> niveauxs = niveauService.findByFiliereId(id); 
    if (niveauxs.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(niveauxs); // Retourne une liste vide avec un statut 404
    }
    return ResponseEntity.ok(niveauxs); // Retourne la liste des niveaux avec un statut 200
}

@PostMapping("/niveaux/ajouter")
    public ResponseEntity<Niveaux> ajouterNiveau(@RequestBody Niveaux niveau) {
        Niveaux nouveauNiveau = niveauService.ajouterNiveau(niveau);
        return ResponseEntity.status(201).body(nouveauNiveau);
    }

@PutMapping("/niveaux/modifier/{id}")
    public ResponseEntity<Niveaux> modifierNiveau(@PathVariable Long id, @RequestBody Niveaux niveau) {
        Niveaux niveauModifie = niveauService.modifierNiveau(id, niveau);
        return ResponseEntity.ok(niveauModifie);
    }

    @DeleteMapping("/niveaux/supprimer/{id}")
    public ResponseEntity<Void> supprimerNiveau(@PathVariable Long id) {
        niveauService.supprimerNiveau(id);
        return ResponseEntity.noContent().build();
    }
}
