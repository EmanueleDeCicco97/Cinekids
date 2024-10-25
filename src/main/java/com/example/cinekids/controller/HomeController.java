package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import com.example.cinekids.model.Film;
import com.example.cinekids.model.Suggerimento;
import com.example.cinekids.service.AdminService;
import com.example.cinekids.service.FilmService;
import com.example.cinekids.service.SuggerimentoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SuggerimentoService suggerimentoService;

    @GetMapping()
    public String gethome(Model model, @RequestParam(name = "error", required = false) String error, HttpSession session,
                          @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento) {
        List<Film> films = filmService.elencoFilm();
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("films", films);
        model.addAttribute("error", error);
        model.addAttribute("errorSuggerimento", errorSuggerimento);

        return "homepage";
    }

    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {
        if (!adminService.loginUtente(email, password, session)) {
            return "redirect:/?error";
        }
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session,
                              @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {
        session.removeAttribute("admin");
        return "redirect:" + redirectUrl;
    }


    @PostMapping("/register")
    public String registerAdmin(HttpSession session,
                                @RequestParam("emailAdminNuovo") String email,
                                @RequestParam("passwordAdminNuovo") String password,
                                @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {

        adminService.inserisciAdmin(email, password);

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/suggerimento")
    public String aggiungiSuggerimento(HttpSession session,
                                       @RequestParam("titoloFilm") String titolo,
                                       @RequestParam("emailSuggerimento") String email,
                                       @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {

        if (!suggerimentoService.inserisciSuggerimento(titolo, email))
            return "redirect:/?errorSuggerimento";

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/eliminaFilm")
    public String eliminaFilm(@RequestParam("id") int idFilm) {
        filmService.eliminaFilm(idFilm);
        return "redirect:/";
    }

}

