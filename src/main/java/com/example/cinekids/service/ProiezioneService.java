package com.example.cinekids.service;

import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;

import java.time.LocalDate;
import java.util.List;

public interface ProiezioneService {
    public Proiezione dettaglioProiezione(int idProiezione);

    public List<Proiezione> elencoProiezioni();

    public void inserisciProiezione(Proiezione proiezione, int idFilm, int idSala);

    public void eliminaProiezione(int idProiezione);

    List<Proiezione> elencoProiezioniByFilm(Film film);
}
