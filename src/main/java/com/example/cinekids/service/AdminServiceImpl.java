package com.example.cinekids.service;

import com.example.cinekids.dao.AdminDao;
import com.example.cinekids.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin dettaglioAdmin(int idAdmin) {
        return adminDao.findById(idAdmin).get();
    }

    @Override
    public void inserisciAdmin(Admin admin) {
        adminDao.save(admin);
    }

    @Override
    public void eliminaAdmin(int idAdmin) {
        adminDao.deleteById(idAdmin);
    }
}
