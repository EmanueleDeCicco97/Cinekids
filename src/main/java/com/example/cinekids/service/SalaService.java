package com.example.cinekids.service;

import com.example.cinekids.model.Sala;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalaService {
    public Sala dettaglioSala(int idSala);

    public List<Sala> elencoSala();

    public void inserisciSala(Sala sala, MultipartFile fotoSala);

    public void eliminaSala(int idSala);
}
