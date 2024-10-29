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
    public Set<Suggerimento> titoliPiuSuggeriti() {
        List<Suggerimento> suggerimenti = elencoSuggerimento();

        // mappo per contare le occorrenze di ciascun titolo
        Map<String, Long> occorrenzeMap = suggerimenti.stream()
                .collect(Collectors.groupingBy(Suggerimento::getTitoloFilm, Collectors.counting()));

        // creo una lista di Suggerimento con occorrenze ordinate per conteggio
        return suggerimenti.stream() // rimuovo i duplicati di suggerimento con lo stesso titolo
                .sorted(Comparator.comparing(
                        suggerimento -> occorrenzeMap.getOrDefault(suggerimento.getTitoloFilm(), 0L), Comparator.reverseOrder()
                ))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }




}