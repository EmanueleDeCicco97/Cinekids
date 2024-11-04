package com.example.cinekids.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sale")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "citta", nullable = false)
    private String citta;

    @Column(name = "nome_sala", nullable = false)
    private String nomeSala;

    @Column(name = "foto_sala")
    private String fotoSala;

    @Column(name = "via_sala")
    private String viaSala;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Proiezione> proiezioni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public String getFotoSala() {
        return fotoSala;
    }

    public void setFotoSala(String fotoSala) {
        this.fotoSala = fotoSala;
    }

    public String getViaSala() {
        return viaSala;
    }

    public void setViaSala(String viaSala) {
        this.viaSala = viaSala;
    }

    public List<Proiezione> getProiezioni() {
        return proiezioni;
    }

    public void setProiezioni(List<Proiezione> proiezioni) {
        this.proiezioni = proiezioni;
    }
}
