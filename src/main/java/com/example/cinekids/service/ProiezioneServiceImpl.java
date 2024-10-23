package com.example.cinekids.service;

import com.example.cinekids.dao.ProiezioneDao;
import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import com.example.cinekids.model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProiezioneServiceImpl implements ProiezioneService {

    @Autowired
    private ProiezioneDao proiezioneDao;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SalaService salaService;

    @Override
    public Proiezione dettaglioProiezione(int idProiezione) {
        return proiezioneDao.findById(idProiezione).get();
    }

    @Override
    public List<Proiezione> elencoProiezioni() {
        return (List<Proiezione>) proiezioneDao.findAll();
    }

    @Override
    public void inserisciProiezione(Proiezione proiezione, LocalDateTime dataOra, int idFilm, int idSala) {
        Film film = filmService.dettaglioFilm(idFilm);
        Sala sala = salaService.dettaglioSala(idSala);
        proiezione.setDataOra(dataOra);
        proiezione.setFilm(film);
        proiezione.setSala(sala);
        proiezioneDao.save(proiezione);
    }

    @Override
    public void eliminaProiezione(int idProiezione) {
        proiezioneDao.deleteById(idProiezione);
    }

    @Override
    public List<Proiezione> elencoProiezioniByFilm(Film film) {
        return proiezioneDao.findByFilm(film);
    }

}
