// package com.gestion_etudiants.gestion_etudiants.models.Users;

// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import jakarta.persistence.*;

// @Entity
// @Table(name="User")
// @Getter
// @Setter
// @NoArgsConstructor
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(length = 150)
//     private String username;

//     @Column(length = 150)
//     private String password;

    // @Column(length = 150)
    // private String role;

//     public User(String username, String password, String role) {
//         this.username = username;
//         this.password = password;
//         this.role = (role == null || role.isEmpty()) ? "etudiant" : role;
//     }
    
// }
