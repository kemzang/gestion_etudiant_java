package com.gestion_etudiants.gestion_etudiants.Controller.Faculte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/faculte/creer")
@ResponseBody // Indique que la réponse est au format JSON
public ResponseEntity<Facultes> createFaculte(@RequestParam String nom) {
    Facultes nouvelleFaculte = new Facultes();
    nouvelleFaculte.setNom(nom);
    faculteServiceImplement.saveFaculte(nouvelleFaculte);
    return ResponseEntity.ok(nouvelleFaculte); // Retourne la faculté nouvellement créée
}

@PostMapping("/faculte/modifier/{id}")
@ResponseBody
public ResponseEntity<Facultes> modifyFaculte(@PathVariable Long id, @RequestParam String nom) {
    Facultes faculte = faculteServiceImplement.findById(id); // Méthode pour trouver par ID
    if (faculte != null) {
        faculte.setNom(nom);
        faculteServiceImplement.saveFaculte(faculte);
        return ResponseEntity.ok(faculte);
    }
    return ResponseEntity.notFound().build();
}

@DeleteMapping("/faculte/supprimer/{id}")
@ResponseBody
public ResponseEntity<Void> deleteFaculte(@PathVariable Long id) {
    faculteServiceImplement.deleteFaculte(id); // Méthode pour supprimer
    return ResponseEntity.ok().build();
}
}