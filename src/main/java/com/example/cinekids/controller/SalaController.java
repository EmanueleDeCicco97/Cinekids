package com.example.cinekids.controller;

import com.example.cinekids.model.Admin;
import com.example.cinekids.model.Sala;
import com.example.cinekids.model.Suggerimento;
import com.example.cinekids.service.AdminService;
import com.example.cinekids.service.SalaService;
import com.example.cinekids.service.SuggerimentoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sale")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SuggerimentoService suggerimentoService;

    @GetMapping()
    public String gethome(Model model, HttpSession session,
                          @RequestParam(name = "error", required = false) String error,
                          @RequestParam(name = "successo", required = false) String successo)
    {
        List<Sala> sale = salaService.elencoSala();
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("sale", sale);
        model.addAttribute("error", error);
        model.addAttribute("successo", successo);


        return "sala";
    }

    @PostMapping("/aggiungiSala")
    public String aggiungiSala(@RequestParam("citta") String citta,
                               @RequestParam("nomeSala") String nomeSala,
                               @RequestParam("fotoSala") MultipartFile fotoSala) {

//        salaService.inserisciSala();

        return "redirect:/sale";
    }



    @GetMapping("/eliminaSala")
    public String eliminaSala(@RequestParam("id") int idSala) {
        salaService.eliminaSala(idSala);
        return "redirect:/sale?successo=Eliminazione effettuata con successo";
    }
}
