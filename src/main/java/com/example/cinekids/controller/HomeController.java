package com.example.cinekids.controller;

import com.example.cinekids.model.Film;
import com.example.cinekids.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
public class HomeController {
    @Autowired
    private FilmService filmService;

    @GetMapping()
    public String gethome(Model model) {
        List<Film> films = filmService.elencoFilm();
        model.addAttribute("films", films);

        return "homepage";
    }
}
