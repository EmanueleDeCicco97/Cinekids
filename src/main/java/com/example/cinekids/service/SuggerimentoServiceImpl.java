package com.example.cinekids.service;

import com.example.cinekids.dao.SuggerimentoDao;
import com.example.cinekids.model.Suggerimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
}
