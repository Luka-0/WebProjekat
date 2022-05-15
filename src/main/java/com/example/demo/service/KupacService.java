package com.example.demo.service;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    public Kupac save(Kupac kupac){
        return kupacRepository.save(kupac);
    }
    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }


}
