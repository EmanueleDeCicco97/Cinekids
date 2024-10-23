package com.example.cinekids.dao;

import com.example.cinekids.model.Suggerimento;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface SuggerimentoDao extends CrudRepository<Suggerimento, Integer> {
    Suggerimento existsByEmailAndDataInvio(String email, LocalDateTime dataInvio);
}
