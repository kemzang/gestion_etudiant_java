package com.gestion_etudiants.gestion_etudiants.Controller.Niveaux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ui.Model;
import java.util.List;

import com.gestion_etudiants.gestion_etudiants.Service.Niveaux.NiveauxServiceImplement;
import com.gestion_etudiants.gestion_etudiants.models.Niveaux.Niveaux;

@Controller
public class NiveauxController {
    @Autowired
    private NiveauxServiceImplement niveauService;
    
    @GetMapping("/niveaux")
    public String listNiveaux(Model model) {
        List<Niveaux> niveaux = niveauService.ListNiveaux();
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

    // @PostMapping("/niveaux/add")
    // public String addNiveau(@RequestParam int niveau, @RequestParam Long filiereId) {
    //     Niveaux newNiveau = new Niveaux();
    //     newNiveau.setNiveau(niveau);
    //     // Assurez-vous d'ajouter la logique pour récupérer la filière correspondante ici
    //     // Par exemple : newNiveau.setFiliere(filiereService.findById(filiereId));
    //     niveauService.save(newNiveau);
    //     return "redirect:/niveaux"; // Redirige vers la liste des niveaux
    // }
}
