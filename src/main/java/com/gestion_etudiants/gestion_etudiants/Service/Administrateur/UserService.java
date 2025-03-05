package com.gestion_etudiants.gestion_etudiants.Service.Administrateur;

import com.gestion_etudiants.gestion_etudiants.models.Administrateur.User;

public interface UserService {
    
    public User registerUser(User user);
    public User findByUsernameAndPasswordAndRole(String username, String password, String role);
}
