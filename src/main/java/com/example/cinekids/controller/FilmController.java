package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import com.example.cinekids.model.Film;
import com.example.cinekids.service.FilmService;
import com.example.cinekids.service.ProiezioneService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private ProiezioneService proiezioneService;

    @GetMapping("/modificaFilmPagina")
    public String modificaFilmPagina(Model model, HttpSession session,
                                     @RequestParam(name = "error", required = false) String error,
                                     @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento,
                                     @RequestParam(name = "id") int idFilm,
                                     @RequestParam(name = "errorAdmin", required = false) String errorAdmin) {


        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/?errorAccessoPagina";
        }

        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("errorSuggerimento", errorSuggerimento);
        model.addAttribute("errorAdmin", errorAdmin);

        // Recupera i dettagli del film
        Film film = filmService.dettaglioFilm(idFilm);
        model.addAttribute("film", film);

        return "modifica_film";
    }

    @PostMapping("/modificaFilm")
    public String modificaFilm(
            @RequestParam("id") int idFilm,
            @RequestParam("titolo") String titolo,
            @RequestParam("genere") String genere,
            @RequestParam("regista") String regista,
            @RequestParam("annoDiUscita") int annoDiUscita,
            @RequestParam("sinossi") String sinossi,
            @RequestParam("trailer") String trailer,
            @RequestParam("locandina") MultipartFile locandina) {

        Film film = filmService.dettaglioFilm(idFilm);
        film.setTitolo(titolo);
        film.setGenere(genere);
        film.setRegista(regista);
        film.setAnnoDiUscita(annoDiUscita);
        film.setSinossi(sinossi);
        film.setTrailer(trailer);

        filmService.insericiFilm(film, locandina);


        return "redirect:/";
    }

    @GetMapping("/aggiungiFilmPagina")
    public String aggiungiFilmPagina(Model model, HttpSession session,
                                     @RequestParam(name = "error", required = false) String error,
                                     @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento,
                                     @RequestParam(name = "errorAdmin", required = false) String errorAdmin) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/?errorAccessoPagina";
        }
        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("errorSuggerimento", errorSuggerimento);
        model.addAttribute("errorAdmin", errorAdmin);


        return "aggiungi_film";
    }

    @PostMapping("/aggiungiFilm")
    public String aggiungiFilm(
            @RequestParam("titolo") String titolo,
            @RequestParam("genere") String genere,
            @RequestParam("regista") String regista,
            @RequestParam("annoDiUscita") int annoDiUscita,
            @RequestParam("sinossi") String sinossi,
            @RequestParam("trailer") String trailer,
            @RequestParam("locandina") MultipartFile locandina,
            @RequestParam("dataOra") LocalDateTime dataOra) {

        Film film = new Film();
        film.setTitolo(titolo);
        film.setGenere(genere);
        film.setRegista(regista);
        film.setAnnoDiUscita(annoDiUscita);
        film.setSinossi(sinossi);
        film.setTrailer(trailer);

        proiezioneService.creaProiezioniPerTutteLeSale(titolo, genere, regista, annoDiUscita, sinossi, trailer, locandina, dataOra);

        return "redirect:/";
    }
}
