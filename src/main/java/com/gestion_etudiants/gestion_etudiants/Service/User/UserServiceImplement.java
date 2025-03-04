package com.gestion_etudiants.gestion_etudiants.Service.User;

import org.springframework.stereotype.Service;

import com.gestion_etudiants.gestion_etudiants.Repository.User.UserRepository;
import com.gestion_etudiants.gestion_etudiants.models.Users.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImplement implements UserService{
    
    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("administrateur"); // Assigne "étudiant" par défaut
        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPasswordAndRole(String matricule, String password, String role) {
        User user = userRepository.findByMatricule(matricule);
        if (user != null && user.getMotpass().equals(password) && user.getRole().equals(role)) {
            return user; // L'utilisateur existe, le mot de passe est correct et le rôle est valide
        }
        return null; // Retourne null si l'utilisateur n'existe pas, le mot de passe est incorrect ou le rôle ne correspond pas
    }
}