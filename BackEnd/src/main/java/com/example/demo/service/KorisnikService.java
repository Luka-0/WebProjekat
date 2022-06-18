package com.example.demo.service;

import com.example.demo.entity.EnumUloga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik save(Korisnik korisnik){
        return korisnikRepository.save(korisnik);
    }

    public Korisnik login(String korisnickoIme, String lozinka) {
        Korisnik korisnik = korisnikRepository.getBykorisnickoIme(korisnickoIme);
        if(korisnik == null || !korisnik.getLozinka().equals(lozinka))
            return null;
        return  korisnik;
    }

    public List<Korisnik> findAllByUlogaOrderById(EnumUloga uloga) {
        return korisnikRepository.findAllByUlogaOrderById(uloga);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Korisnik findByKorisnickoIme(String userName){
        Korisnik korisnik = korisnikRepository.getBykorisnickoIme(userName);

        if(korisnik == null){
            return null;
        }
        return korisnik;
    }
}
