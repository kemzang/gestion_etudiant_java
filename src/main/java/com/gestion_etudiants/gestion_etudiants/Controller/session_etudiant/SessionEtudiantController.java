package com.gestion_etudiants.gestion_etudiants.Controller.session_etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gestion_etudiants.gestion_etudiants.Service.Etudiant.EtudiantServiceImpl;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionEtudiantController {

    @Autowired
    private EtudiantServiceImpl userService;

    @GetMapping("/session_etudiant")
public String showSessionEtudiant(HttpSession session, Model model) {
    Etudiant user = (Etudiant) session.getAttribute("user");
    if (user != null) {
        model.addAttribute("prenom", user.getPrenom());
        model.addAttribute("nom", user.getNom());
        model.addAttribute("matricule", user.getMatricule());
        model.addAttribute("password", user.getMotpass());
        model.addAttribute("attestation_scolarite", user.getPdfAttestationScolarite());
        model.addAttribute("carte_etudiant", user.getPdfCarteEtudiant());
    } else {
        model.addAttribute("errorMessage", "Aucun utilisateur connecté.");
    }
    return "session_etudiant"; // Renvoie la vue
}

    @PostMapping("/session_etudiant")
    public String loginUser(@ModelAttribute Etudiant user, HttpSession session, Model model) {
        Etudiant existingUser = userService.findByUsernameAndPasswordAndRole(user.getMatricule(), user.getMotpass(), user.getRole());

        if (existingUser != null && "etudiant".equals(user.getRole())) {
            session.setAttribute("user", existingUser); // Démarre une nouvelle session
            model.addAttribute("nom", existingUser.getNom());
            model.addAttribute("prenom", existingUser.getPrenom());
            model.addAttribute("matricule", existingUser.getMatricule());
            model.addAttribute("password", existingUser.getMotpass());
            model.addAttribute("attestation_scolarite", "/etudiants/" + user.getMatricule() + "/pdfs");
            model.addAttribute("carte_etudiant", "/etudiants-carte/" + user.getMatricule() + "/pdfs");
            return "session_etudiant"; // Renvoie à la page session_etudiant
        } else if (existingUser != null && "administrateur".equals(user.getRole())) {
            session.setAttribute("user", existingUser); // Démarre une nouvelle session
            return "redirect:/administration"; // Redirige vers la page d'administration
        } else {
            model.addAttribute("errorMessage", "Nom d'utilisateur, mot de passe ou rôle incorrect.");
            return "session_etudiant"; // Renvoie à la page de connexion avec un message d'erreur
        }
    }
}