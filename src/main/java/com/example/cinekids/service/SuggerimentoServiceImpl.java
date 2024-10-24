package com.example.cinekids.service;

import com.example.cinekids.dao.SuggerimentoDao;
import com.example.cinekids.model.Suggerimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean inserisciSuggerimento(Suggerimento suggerimento) {
        int annoInvio = suggerimento.getDataInvio().getYear();  // Ottieni l'anno della data di invio

        List<Suggerimento> suggerimenti = suggerimentoDao.findAllByEmail(suggerimento.getEmail());  // Recupera tutti i suggerimenti con la stessa email

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
