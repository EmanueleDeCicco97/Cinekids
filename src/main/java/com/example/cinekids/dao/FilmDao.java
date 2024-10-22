package com.example.cinekids.dao;

import com.example.cinekids.model.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmDao extends CrudRepository<Film, Integer> {
}
