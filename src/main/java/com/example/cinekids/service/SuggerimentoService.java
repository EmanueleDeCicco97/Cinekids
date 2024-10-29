package com.example.cinekids.service;

import com.example.cinekids.model.Suggerimento;

import java.util.List;
import java.util.Set;

public interface SuggerimentoService {
    public Suggerimento dettaglioSuggerimento(int idSuggerimento);

    public List<Suggerimento> elencoSuggerimento();

    public boolean inserisciSuggerimento(String titolo, String email);

    public void eliminaSuggerimento(int idSuggerimento);

    public Set<Suggerimento> titoliPiuSuggeriti();

}
