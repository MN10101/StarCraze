package com.example.starcraze.services;

import com.example.starcraze.entity.Authority;
import java.util.List;

public interface AuthorityService {

    // CRUD OPERATIONS
    // READ ALL
    List<Authority> findAll();

    // READ BY ID
    Authority findById(Long id);

    // CREATE
    void save(Authority authority);

    // UPDATE
    void update(Authority authority);

    // DELETE
    void deleteById(Long id);
}
