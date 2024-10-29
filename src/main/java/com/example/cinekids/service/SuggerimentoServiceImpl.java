package com.example.cinekids.service;

import com.example.cinekids.dao.SuggerimentoDao;
import com.example.cinekids.model.Suggerimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class SuggerimentoServiceImpl implements SuggerimentoService {

    @Autowired
    private SuggerimentoDao suggerimentoDao;

    @Override
    public Suggerimento dettaglioSuggerimento(int idSuggerimento) {
        return suggerimentoDao.findById(idSuggerimento).get();
    }

    @Override
    public List<Suggerimento> elencoSuggerimento() {
        return (List<Suggerimento>) suggerimentoDao.findAll();
    }

    @Override
    public boolean inserisciSuggerimento(String titolo, String email) {
        Suggerimento suggerimento = new Suggerimento();
        suggerimento.setTitoloFilm(titolo);
        suggerimento.setEmail(email);
        suggerimento.setDataInvio(LocalDateTime.now());

        int annoInvio = suggerimento.getDataInvio().getYear();

        List<Suggerimento> suggerimenti = suggerimentoDao.findAllByEmail(suggerimento.getEmail());

        boolean esisteSuggerimentoPerAnno = suggerimenti.stream()
                .anyMatch(s -> s.getDataInvio().getYear() == annoInvio);

        if (!esisteSuggerimentoPerAnno) {
            suggerimentoDao.save(suggerimento);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void eliminaSuggerimento(int idSuggerimento) {
        suggerimentoDao.deleteById(idSuggerimento);
    }


    @Override
    public List<Suggerimento> titoliPiuSuggeriti() {
        List<Suggerimento> titoliSuggeriti = elencoSuggerimento();

        // Raggruppa i suggerimenti per titolo e conta quante volte appare ciascun titolo
        Map<String, Long> conteggi = titoliSuggeriti.stream()
                .collect(Collectors.groupingBy(
                        Suggerimento::getTitoloFilm,  // Gruppo per titolo
                        Collectors.counting()          // Conta quante volte appare ciascun titolo
                ));

        // Crea una lista di suggerimenti ordinati per conteggio
        return conteggi.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Ordina per conteggio decrescente
                .map(entry -> titoliSuggeriti.stream()
                        .filter(s -> s.getTitoloFilm().equalsIgnoreCase(entry.getKey()))
                        .findFirst() // Prende il primo suggerimento corrispondente
                        .orElse(null)) // Se non esiste, restituisce null
                .filter(Objects::nonNull) // Filtra i valori null
                .collect(Collectors.toList()); // Ritorna la lista di suggerimenti ordinati
    }

}