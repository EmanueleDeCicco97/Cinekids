package com.example.cinekids.service;

import com.example.cinekids.model.Proiezione;

import java.util.List;

public interface ProiezioneService {
    public Proiezione dettaglioProiezione(int idProiezione);
    public List<Proiezione> elencoProiezioni();
    public void inserisciProiezione(Proiezione proiezione, int idFilm, int idSala);
    public void eliminaProiezione(int idProiezione);
}
