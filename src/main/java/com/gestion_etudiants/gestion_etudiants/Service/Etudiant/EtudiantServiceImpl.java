package com.gestion_etudiants.gestion_etudiants.Service.Etudiant;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.Repository.EtudiantRepository;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;

import lombok.AllArgsConstructor;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    
    @Override
    public Etudiant createEtudiant(Etudiant etudiant) {
        // Validation basique, par exemple, vérifier si le matricule est déjà utilisé
        if (etudiantRepository.findByMatricule(etudiant.getMatricule()).isPresent()) {
            throw new IllegalArgumentException("Le matricule est déjà utilisé.");
        }
    
        // Assigner le rôle par défaut si non spécifié
        if (etudiant.getRole() == null || etudiant.getRole().isEmpty()) {
            etudiant.setRole("etudiant"); // Assigne "étudiant" par défaut
        }
        return etudiantRepository.save(etudiant);
    }

    public Optional<Etudiant> findEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    
    @Override
    public Etudiant findByUsernameAndPasswordAndRole(String matricule, String password, String role) {
        Etudiant user = findEtudiantByMatricule(matricule).orElse(null);
        if (user != null && user.getMotpass().equals(password) && user.getRole().equals(role)) {
            return user; // L'utilisateur existe, le mot de passe est correct et le rôle est valide
        }
        return null; // Retourne null si l'utilisateur n'existe pas, le mot de passe est incorrect ou le rôle ne correspond pas
    }


    @Override
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    

    @Override
    public byte[][] getEtudiantPDFsByMatricule(String matricule) {
        Optional<Etudiant> etudiant = getEtudiantByMatricule(matricule);
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            return new byte[][]{e.getPdfAttestationScolarite(), e.getPdfCarteEtudiant()};
        } else {
            return null; 
        }
    }

    @Override
    public byte[] getEtudiantPDFByMatricule(String matricule) {
        Optional<Etudiant> etudiant = getEtudiantByMatricule(matricule); // Use correct method name
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            return e.getPdfAttestationScolarite(); // Return the correct PDF
        } else {
            return null; // Or throw an exception if the student doesn't exist
        }
    }

    @Override
    public byte[] getEtudiantPDFByCarteEtudiant(String matricule) {
        Optional<Etudiant> etudiant = getEtudiantByMatricule(matricule); // Use correct method name
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            return e.getPdfCarteEtudiant(); // Return the correct PDF
        } else {
            return null; // Or throw an exception if the student doesn't exist
        }
    }
    
}
