package com.gestion_etudiants.gestion_etudiants.models.Administrateur;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name="User")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String matricule;

    @Column(length = 50)
    private String motpass;

    @Column(length = 150)
    private String role;

    public User(String matricule, String motpass, String role) {
        this.matricule = matricule;
        this.motpass = motpass;
        this.role = (role == null || role.isEmpty()) ? "administrateur" : role;
    }
    
}
