package com.example.cinekids.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "proiezioni")
public class Proiezione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data")
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "fk_id_film", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "fk_id_sala", nullable = false)
    private Sala sala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
