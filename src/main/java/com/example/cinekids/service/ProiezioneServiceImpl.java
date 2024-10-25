package com.example.cinekids.service;

import com.example.cinekids.dao.ProiezioneDao;
import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import com.example.cinekids.model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProiezioneServiceImpl implements ProiezioneService {

    @Autowired
    private ProiezioneDao proiezioneDao;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SalaService salaService;

    @Override
    public Proiezione dettaglioProiezione(int idProiezione) {
        return proiezioneDao.findById(idProiezione).get();
    }

    @Override
    public List<Proiezione> elencoProiezioni() {
        return (List<Proiezione>) proiezioneDao.findAll();
    }

//    @Override
//    public void inserisciProiezione(LocalDateTime dataOra, int idFilm, int idSala) {
//        Proiezione proiezione = new Proiezione();
//        Film film = filmService.dettaglioFilm(idFilm);
//        Sala sala = salaService.dettaglioSala(idSala);
//        proiezione.setDataOra(dataOra);
//        proiezione.setFilm(film);
//        proiezione.setSala(sala);
//        proiezioneDao.save(proiezione);
//    }

    @Override
    public void eliminaProiezione(int idProiezione) {
        proiezioneDao.deleteById(idProiezione);
    }


    @Override
    public void creaProiezioniPerTutteLeSale(String titolo, String genere, String regista,
                                             int annoDiUscita, String sinossi, String trailer,
                                             MultipartFile locandina, LocalDateTime dataOra) {
        Film film = new Film();
        film.setTitolo(titolo);
        film.setGenere(genere);
        film.setRegista(regista);
        film.setAnnoDiUscita(annoDiUscita);
        film.setSinossi(sinossi);
        film.setTrailer(trailer);
        filmService.insericiFilm(film, locandina);

        // recupero tutte le sale
        List<Sala> sale = salaService.elencoSala();

        // creo una proiezione per ogni sala
        for (Sala sala : sale) {
            Proiezione proiezione = new Proiezione();
            proiezione.setFilm(film);
            proiezione.setSala(sala);
            proiezione.setDataOra(dataOra);
            proiezioneDao.save(proiezione);
        }
    }


}
