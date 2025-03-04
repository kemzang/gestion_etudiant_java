// package com.gestion_etudiants.gestion_etudiants.Service.Faculte;

// import com.gestion_etudiants.gestion_etudiants.Repository.Faculte.FacuteRepository;
// import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
// import java.util.*;

// // import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class FaculteServiceImplement implements FaculteService{
    
//         private  FacuteRepository faculteRepository;



package com.gestion_etudiants.gestion_etudiants.Service.Faculte;

import com.gestion_etudiants.gestion_etudiants.Repository.Faculte.FacuteRepository;
import com.gestion_etudiants.gestion_etudiants.models.Faculte.Facultes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaculteServiceImplement implements FaculteService {

    @Autowired
    private FacuteRepository faculteRepository; // Injection du repository

    @Override
    public List<Facultes> ListFaculte() {
        return faculteRepository.findAll(); // Appel au repository pour récupérer toutes les facultés
    }
}