package com.gestion_etudiants.gestion_etudiants.Service.Etudiant;

import java.util.Optional;

// import com.gestion_etudiants.gestion_etudiants.Repository.EtudiantRepository;
import com.gestion_etudiants.gestion_etudiants.models.Etudiant.Etudiant;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
import com.itextpdf.layout.element.List;

import java.util.*;

// import java.util.List;

public interface EtudiantService {
    Etudiant createEtudiant(Etudiant etudiant);

    // Optional<Etudiant> trouverEtudiantParMatricule(String matricule);

    java.util.List<Etudiant> listEtudiants();
}