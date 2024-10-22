package com.example.cinekids.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    @Pattern(
            regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,30}$",
            message = "Formato email non valido. Esempio di email valida: esempio@email.com"
    )
    private String email;

    @Column(name = "password", nullable = false)
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\\-\\.,\\$àèìòù])[A-Za-z\\d\\-\\.,\\$àèìòù]+$",
            message = "La password deve contenere almeno una lettera, un numero e un carattere speciale tra: -.,$àèìòù. Esempio di password valida: Pa$$w0rdàè."
    )
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
