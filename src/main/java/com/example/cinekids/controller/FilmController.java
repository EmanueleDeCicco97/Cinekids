package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import com.example.cinekids.model.Film;
import com.example.cinekids.service.FilmService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/modificaFilmPagina")
    public String modificaFilm(Model model, HttpSession session,
                               @RequestParam(name = "error", required = false) String error,
                               @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento,
                               @RequestParam(name = "id") int idFilm,
                               @RequestParam(name = "errorAdmin", required = false) String errorAdmin) {


        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("errorAdmin", errorAdmin);
        model.addAttribute("errorSuggerimento", errorSuggerimento);
        Film film = filmService.dettaglioFilm(idFilm);
        model.addAttribute("film", film);


        return "modifica_film";
    }

    @PostMapping("/modificaFilm")
    @ResponseBody
    public String modificaFilm(@ModelAttribute("film") Film film) {
        return "ok";
    }

    @GetMapping("/aggiungiFilmPagina")
    public String aggiungiFilm(Model model, HttpSession session,
                               @RequestParam(name = "error", required = false) String error,
                               @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("errorSuggerimento", errorSuggerimento);


        return "aggiungi_film";
    }
}
