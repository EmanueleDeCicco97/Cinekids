package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/modificaFilm")
public class ModificaFilmController {

    @GetMapping()
    public String aggiungiSala(Model model, HttpSession session,
                               @RequestParam(name = "error", required = false) String error,
                               @RequestParam(name = "errorSuggerimento", required = false) String errorSuggerimento) {
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("error", error);
        model.addAttribute("errorSuggerimento", errorSuggerimento);

        return "modifica_film";
    }
}
