package com.example.cinekids.service;

import com.example.cinekids.model.Admin;
import jakarta.servlet.http.HttpSession;

public interface AdminService {
    public Admin dettaglioAdmin(int idAdmin);

    public void inserisciAdmin(String email, String password);

    public void eliminaAdmin(int idAdmin);

    public boolean loginUtente(String email, String password, HttpSession session);
}
