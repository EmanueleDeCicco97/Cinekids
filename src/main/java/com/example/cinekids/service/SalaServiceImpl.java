package com.example.cinekids.service;

import com.example.cinekids.dao.SalaDao;
import com.example.cinekids.model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaDao salaDao;

    @Override
    public Sala dettaglioSala(int idSala) {
        return salaDao.findById(idSala).get();
    }

    @Override
    public List<Sala> elencoSala() {
        return (List<Sala>) salaDao.findAll();
    }

    @Override
    public void inserisciSala(Sala sala, MultipartFile fotoSala) {
        if (fotoSala != null && !fotoSala.isEmpty()) {
            try {
                String formato = fotoSala.getContentType();
                String copertinaCodificata = "data:" + formato + ";base64," +
                        Base64.getEncoder().encodeToString(fotoSala.getBytes());
                sala.setFotoSala(copertinaCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        salaDao.save(sala);
    }

    @Override
    public void eliminaSala(int idSala) {
        salaDao.deleteById(idSala);
    }
}
