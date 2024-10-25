package com.example.cinekids.service;

import com.example.cinekids.dao.AdminDao;
import com.example.cinekids.model.Admin;
import jakarta.servlet.http.HttpSession;
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
    public boolean inserisciAdmin(String email, String password) {

        if(adminDao.findByEmail(email) != null)
            return true;

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        adminDao.save(admin);
        return false;
    }

    @Override
    public void eliminaAdmin(int idAdmin) {
        adminDao.deleteById(idAdmin);
    }

    @Override
    public boolean loginUtente(String email, String password, HttpSession session) {
        Admin admin = adminDao.findByEmailAndPassword(email, password);
        if (admin != null) {
            session.setAttribute("admin", admin);
            return true;
        }
        return false;
    }
}
