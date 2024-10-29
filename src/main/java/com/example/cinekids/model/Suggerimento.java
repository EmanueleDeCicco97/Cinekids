package com.example.cinekids.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "suggerimenti")
public class Suggerimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "titolo_film", nullable = false)
    private String titoloFilm;

    @Column(name = "data_invio")
    private LocalDateTime dataInvio;

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

    public String getTitoloFilm() {
        return titoloFilm;
    }

    public void setTitoloFilm(String titoloFilm) {
        this.titoloFilm = titoloFilm;
    }

    public LocalDateTime getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(LocalDateTime dataInvio) {
        this.dataInvio = dataInvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Suggerimento)) return false;
        Suggerimento that = (Suggerimento) o;
        return Objects.equals(titoloFilm, that.titoloFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titoloFilm);
    }
}
