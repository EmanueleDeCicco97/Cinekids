package com.example.cinekids.controller;

import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import com.example.cinekids.service.FilmService;
import com.example.cinekids.service.ProiezioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private ProiezioneService proiezioniService;


    @GetMapping()
    public String dettaglio(Model model, @RequestParam(name = "id") int idFilm) {

        Film film = filmService.dettaglioFilm(idFilm);

        Set<LocalDateTime> dateUniche = film.getProiezioni().stream()
                .map(Proiezione::getDataOra)
                .collect(Collectors.toSet());

        model.addAttribute("film", film);
        model.addAttribute("date", dateUniche);

        return "dettaglio";
    }
}
