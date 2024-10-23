package com.example.cinekids.controller;

import com.example.cinekids.model.Film;
import com.example.cinekids.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    FilmService filmService;

    @GetMapping()
    public String dettaglio(Model model, @RequestParam(name = "id") int idFilm) {
        Film film = filmService.dettaglioFilm(idFilm);
        model.addAttribute("film", film);
        return "dettaglio";
    }
}
