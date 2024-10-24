package com.example.cinekids.dao;

import com.example.cinekids.model.Suggerimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SuggerimentoDao extends CrudRepository<Suggerimento, Integer> {
    List<Suggerimento> findAllByEmail(String email);
}
