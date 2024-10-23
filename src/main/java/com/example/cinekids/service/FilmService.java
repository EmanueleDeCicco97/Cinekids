package com.example.cinekids.service;

import com.example.cinekids.model.Film;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface FilmService {
    public Film dettaglioFilm(int idFilm);

    public List<Film> elencoFilm();

    public void insericiFilm(Film film, MultipartFile locandina);

    public void eliminaFilm(int idFilm);
}
