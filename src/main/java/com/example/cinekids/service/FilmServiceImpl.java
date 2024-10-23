package com.example.cinekids.service;

import com.example.cinekids.dao.FilmDao;
import com.example.cinekids.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmDao filmDao;

    @Override
    public Film dettaglioFilm(int idFilm) {
        return filmDao.findById(idFilm).get();
    }

    @Override
    public List<Film> elencoFilm() {
        return (List<Film>) filmDao.findAll();
    }

    @Override
    public void insericiFilm(Film film) {
        filmDao.save(film);

    }

    @Override
    public void eliminaFilm(int idFilm) {
        filmDao.deleteById(idFilm);
    }
}
