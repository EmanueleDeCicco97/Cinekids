package com.example.cinekids.dao;

import com.example.cinekids.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminDao extends CrudRepository<Admin, Integer> {
    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);

}
