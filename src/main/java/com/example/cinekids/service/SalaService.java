package com.example.cinekids.service;

import com.example.cinekids.model.Sala;

import java.util.List;

public interface SalaService {
    public Sala dettaglioSala(int idSala);
    public List<Sala> elencoSala();
    public void inserisciSala(Sala sala);
    public void eliminaSala(int idSala);
}
