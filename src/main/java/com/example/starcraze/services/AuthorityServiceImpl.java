package com.example.starcraze.services;

import com.example.starcraze.repository.AuthorityRepository;
import com.example.starcraze.entity.Authority;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }


    // CRUD OPERATIONS


    // READ ALL
    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    // READ BY ID
    @Override
    public Authority findById(Long id) {
        Optional<Authority> result = authorityRepository.findById(id);

        Authority authority = null;

        if (result.isPresent()) {
            authority = result.get();
        } else {
            throw new RuntimeException("Authority with id " + id + " not found.");
        }
        return authority;
    }

    // CREATE
    @Override
    @Transactional
    public void save(Authority authority) {
        authorityRepository.save(authority);
    }



    // UPDATE
    @Override
    public void update(Authority authority) {
        authorityRepository.save(authority);
    }

    // DELETE
    @Override
    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }

}
