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

        // Mappa per contare le occorrenze e un set per i titoli unici
        Map<String, Long> occorrenzeMap = new HashMap<>();
        Set<Suggerimento> risultatiFinali = new LinkedHashSet<>();

        for (Suggerimento suggerimento : suggerimenti) {
            String titoloLower = suggerimento.getTitoloFilm().toLowerCase();

            // Contare le occorrenze
            occorrenzeMap.put(titoloLower, occorrenzeMap.getOrDefault(titoloLower, 0L) + 1);

            // Aggiungere solo un suggerimento per titolo unico
            if (risultatiFinali.stream().noneMatch(s -> s.getTitoloFilm().equalsIgnoreCase(suggerimento.getTitoloFilm()))) {
                risultatiFinali.add(suggerimento);
            }
        }

        // Ordinare i risultati in base alle occorrenze
        return risultatiFinali.stream()
                .sorted(Comparator.comparing(s -> occorrenzeMap.get(s.getTitoloFilm().toLowerCase()), Comparator.reverseOrder()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

}