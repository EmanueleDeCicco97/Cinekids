package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import com.example.cinekids.service.FilmService;
import com.example.cinekids.service.ProiezioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

    @Autowired
    private FilmService filmService;


    @GetMapping()
    public String dettaglio(Model model, @RequestParam(name = "id") int idFilm,
                                         HttpSession session,
                                         @RequestParam(name = "error", required = false) String error,
                                         @RequestParam(name = "successo", required = false) String successo) {

        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("successo", successo);


        Film film = filmService.dettaglioFilm(idFilm);
        Set<LocalDateTime> dateUniche = film.getProiezioni().stream()
                .map(Proiezione::getDataOra)
                .collect(Collectors.toSet());

        model.addAttribute("film", film);
        model.addAttribute("date", dateUniche);

        return "dettaglio";
    }
}
