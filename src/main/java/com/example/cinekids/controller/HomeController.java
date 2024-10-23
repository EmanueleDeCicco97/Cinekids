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
    public String gethome(Model model, @RequestParam(name="error",required = false ) String error, HttpSession session,
                                       @RequestParam(name="errorSuggerimento",required = false ) String errorSuggerimento) {
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
                        @RequestParam("password") String password) {
        if (!adminService.loginUtente(email, password, session))
            return "redirect:/?error";

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerAdmin(HttpSession session,
                                @RequestParam("emailAdminNuovo") String email,
                                @RequestParam("passwordAdminNuovo") String password) {
        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);

        adminService.inserisciAdmin(admin);

        return "redirect:/";
    }

    @PostMapping("/suggerimento")
    public String aggiungiSuggerimento(HttpSession session,
                                       @RequestParam("titoloFilm") String titolo,
                                       @RequestParam("emailSuggerimento") String email) {
        Suggerimento suggerimento = new Suggerimento();
        suggerimento.setTitoloFilm(titolo);
        suggerimento.setEmail(email);

        if (suggerimentoService.inserisciSuggerimento(suggerimento))
        return "redirect:/?errorSuggerimento";

        return "redirect:/";
    }
}

