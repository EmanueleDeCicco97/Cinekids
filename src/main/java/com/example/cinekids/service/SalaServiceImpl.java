package com.example.cinekids.service;

import com.example.cinekids.dao.SalaDao;
import com.example.cinekids.model.Sala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaDao salaDao;

    @Override
    public Sala dettaglioSala(int idSala) {
        return  salaDao.findById(idSala).get();
    }

    @Override
    public List<Sala> elencoSala() {
        return (List<Sala>) salaDao.findAll();
    }

    @Override
    public void inserisciSala(Sala sala) {
        salaDao.save(sala);
    }

    @Override
    public void eliminaSala(int idSala) {  
        salaDao.deleteById(idSala);
    }
}
