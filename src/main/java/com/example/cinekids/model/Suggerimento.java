package com.example.cinekids.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "suggerimenti")
public class Suggerimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,30}$", message = "Formato email non valido (MarioRossi@gmail.com)")
    private String email;

    @Column(name = "titolo_film", nullable = false)
    private String titolo_film;

    @Column(name = "data_invio")
    private LocalDateTime data_invio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,30}$", message = "Formato email non valido (MarioRossi@gmail.com)") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,30}$", message = "Formato email non valido (MarioRossi@gmail.com)") String email) {
        this.email = email;
    }

    public String getTitolo_film() {
        return titolo_film;
    }

    public void setTitolo_film(String titolo_film) {
        this.titolo_film = titolo_film;
    }

    public LocalDateTime getData_invio() {
        return data_invio;
    }

    public void setData_invio(LocalDateTime data_invio) {
        this.data_invio = data_invio;
    }
}
