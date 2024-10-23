package com.example.cinekids.service;

import com.example.cinekids.model.Film;

import java.util.List;

public interface FilmService {
    public Film dettaglioFilm(int idFilm);
    public List<Film> elencoFilm();
    public void insericiFilm(Film film);
    public void eliminaFilm(int idFilm);
}
