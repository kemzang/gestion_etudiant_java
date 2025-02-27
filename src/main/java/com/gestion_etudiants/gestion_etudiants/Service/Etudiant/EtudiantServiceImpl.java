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
        // TODO Auto-generated method stub
        return etudiantRepository.save(etudiant);
    }

    // public Optional<Etudiant> trouverEtudiantParMatricule(String matricule) {
    //     return etudiantRepository.findByMatricule(matricule);
    // }

    @Override
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    

    // Méthode pour récupérer les PDF
    public byte[][] getEtudiantPDFsByMatricule(String matricule) {
        Optional<Etudiant> etudiant = getEtudiantByMatricule(matricule);
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            return new byte[][]{e.getPdfAttestationScolarite(), e.getPdfCarteEtudiant()};
        } else {
            return null; // ou throw une exception si l'étudiant n'existe pas
        }
    }

    public byte[] getEtudiantPDFByMatricule(String matricule) {
        Optional<Etudiant> etudiant = getEtudiantByMatricule(matricule); // Use correct method name
        if (etudiant.isPresent()) {
            Etudiant e = etudiant.get();
            return e.getPdfAttestationScolarite(); // Return the correct PDF
        } else {
            return null; // Or throw an exception if the student doesn't exist
        }
    }

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
