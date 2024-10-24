package com.example.cinekids.service;

import com.example.cinekids.model.Suggerimento;

import java.util.List;

public interface SuggerimentoService {
    public Suggerimento dettaglioSuggerimento(int idSuggerimento);

    public List<Suggerimento> elencoSuggerimento();

    public boolean inserisciSuggerimento(Suggerimento suggerimento);

    public void eliminaSuggerimento(int idSuggerimento);

}
