package com.gestion_etudiants.gestion_etudiants.Controller.Departement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Departements> departements = departementServiceImplement.ListDepartement();
        List<Facultes> facultes = faculteService.ListFaculte(); // Récupération de toutes les facultés
        model.addAttribute("facultes", facultes);
        model.addAttribute("departements", departements);
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

    @PostMapping("/departements/ajouter")
    public ResponseEntity<Departements> addDepartement(@RequestBody Departements departement) {
        try {
            // Vérifiez que la faculté associée existe
            if (departement.getFaculte() == null || departement.getFaculte().getId() == null) {
                return ResponseEntity.badRequest().body(null); // Faculté non spécifiée
            }
    
            Facultes faculte = faculteService.findById(departement.getFaculte().getId());
            if (faculte == null) {
                return ResponseEntity.badRequest().body(null); // Faculté non trouvée
            }
            
            // Associez la faculté au département
            departement.setFaculte(faculte);
    
            Departements savedDepartement = departementServiceImplement.save(departement);
            return ResponseEntity.ok(savedDepartement);
        } catch (Exception e) {
            e.printStackTrace(); // Log de l'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Modifier un département existant
    @PutMapping("/departements/modifier/{id}")
public ResponseEntity<Departements> updateDepartement(@PathVariable Long id, @RequestBody Departements departement) {
    try {
        departementServiceImplement.update(id, departement);
        return ResponseEntity.ok(departement);
    } catch (Exception e) {
        e.printStackTrace(); // Log de l'erreur
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    // Supprimer un département par ID
    @DeleteMapping("/departements/supprimer/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        departementServiceImplement.delete(id);
        return ResponseEntity.ok().build();
    }
}