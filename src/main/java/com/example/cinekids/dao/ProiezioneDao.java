package com.example.cinekids.dao;

import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProiezioneDao extends CrudRepository<Proiezione, Integer> {

    List<Proiezione> findByFilm(Film film);

}
