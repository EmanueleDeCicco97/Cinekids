package com.example.cinekids.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "genere", nullable = false)
    private String genere;

    @Column(name = "regista", nullable = false)
    private String regista;

    @Column(name = "sinossi", nullable = false)
    private String sinossi;

    @Column(name = "locandina", nullable = true)
    private String locandina;

    @Column(name = "trailer", nullable = true)
    private String trailer;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Proiezione> proiezioni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    public String getSinossi() {
        return sinossi;
    }

    public void setSinossi(String sinossi) {
        this.sinossi = sinossi;
    }

    public String getLocandina() {
        return locandina;
    }

    public void setLocandina(String locandina) {
        this.locandina = locandina;
    }

    public List<Proiezione> getProiezioni() {
        return proiezioni;
    }

    public void setProiezioni(List<Proiezione> proiezioni) {
        this.proiezioni = proiezioni;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
