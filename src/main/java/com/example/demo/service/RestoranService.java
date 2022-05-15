package com.example.demo.service;

import com.example.demo.entity.Restoran;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Lokacija;

import com.example.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    //pronalazenje svih restorana
    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }
    //pronalazenje restorana po nazivu
    public Restoran finOneByNaziv(String naziv){
        Optional<Restoran> r = restoranRepository.findByNaziv(naziv);

        if(r.isPresent()){
            return r.get();
        }
        return null;
    }

    //pronalazenje  restorana po odredjenoj lokaciji
    public Restoran findOneByLokacija(Lokacija lokacija) {
        Optional<Restoran> r = restoranRepository.findByLokacija(lokacija);

        if(r.isPresent()){
            return r.get();
        }

        return null;
    }
    //pronalazenje restorana po odredjenom tipu
    public Restoran findOneByTipRestorana(String tip){
        Optional<Restoran> r = restoranRepository.findByTipRestorana(tip);

        if(r.isPresent()){
            return  r.get();
        }
        return null;
    }
}
