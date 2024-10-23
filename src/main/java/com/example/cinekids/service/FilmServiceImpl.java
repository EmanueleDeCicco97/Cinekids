package com.example.cinekids.service;

import com.example.cinekids.dao.FilmDao;
import com.example.cinekids.model.Film;
import com.example.cinekids.model.Proiezione;
import com.example.cinekids.model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmDao filmDao;

//    @Autowired
//    private SalaService salaService;
//
//    @Autowired
//    private ProiezioneService proiezioneService;

    @Override
    public Film dettaglioFilm(int idFilm) {
        return filmDao.findById(idFilm).get();
    }

    @Override
    public List<Film> elencoFilm() {
        return (List<Film>) filmDao.findAll();
    }

    @Override
    public void insericiFilm(Film film, LocalDate data, MultipartFile locandina) {

        if (locandina != null && !locandina.isEmpty()) {
            try {
                String formato = locandina.getContentType();
                String copertinaCodificata = "data:" + formato + ";base64," +
                        Base64.getEncoder().encodeToString(locandina.getBytes());
                film.setLocandina(copertinaCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else{
            film.setLocandina("/static/markerImage.png");
        }

        filmDao.save(film);

        // ricavo tutte le sale per associare il film aggiunto a esse
//        List<Sala> sale = salaService.elencoSala();
//        for (Sala sala : sale) {
//            Proiezione proiezione = new Proiezione();
//            proiezione.setData(data);
//            proiezioneService.inserisciProiezione(proiezione, film.getId(), sala.getId());
//        }
    }

    @Override
    public void eliminaFilm(int idFilm) {
        filmDao.deleteById(idFilm);
    }
}
