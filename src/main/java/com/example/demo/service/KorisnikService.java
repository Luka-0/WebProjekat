package com.example.demo.service;

import com.example.demo.entity.Korisnik;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }











}
