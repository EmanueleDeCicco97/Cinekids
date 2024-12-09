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
import java.util.Set;

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
                          @RequestParam(name = "successo", required = false) String successo) {

        List<Film> films = filmService.elencoFilm();
        Set<Suggerimento> filmSuggeriti = suggerimentoService.titoliPiuSuggeriti();
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("films", films);
        model.addAttribute("filmSuggeriti", filmSuggeriti);
        model.addAttribute("error", error);
        model.addAttribute("successo", successo);

        return "homepage";
    }

    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {
        if (!adminService.loginUtente(email, password, session)) {
            String separatore = redirectUrl.contains("?") ? "&" : "?";
            return "redirect:" + redirectUrl + separatore + "error=Credenziali sbagliate. Riprova.";
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
        String messaggio = adminService.inserisciAdmin(email, password) ?
                "successo=Inserimento avvenuto con successo!" :
                "error=Esiste gia un Admin con la stessa email inserita";

        String separatore = redirectUrl.contains("?") ? "&" : "?";
        return "redirect:" + redirectUrl + separatore + messaggio;
    }


    @PostMapping("/suggerimento")
    public String aggiungiSuggerimento(HttpSession session,
                                       @RequestParam("titoloFilm") String titolo,
                                       @RequestParam("emailSuggerimento") String email,
                                       @RequestParam(name = "redirectUrl", defaultValue = "/") String redirectUrl) {
        String messaggio = suggerimentoService.inserisciSuggerimento(titolo, email) ?
                "successo=Inserimento avvenuto con successo!" :
                "error=Suggerimento gia inserito per questa email, puoi inviarne solo uno all'anno";

        String separatore = redirectUrl.contains("?") ? "&" : "?";
        return "redirect:" + redirectUrl + separatore + messaggio;
    }


}

