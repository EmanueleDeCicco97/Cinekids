package com.example.cinekids.service;

import com.example.cinekids.model.Admin;

public interface AdminService {
    public Admin dettaglioAdmin(int idAdmin);

    public void inserisciAdmin(Admin admin);

    public void eliminaAdmin(int idAdmin);
}
