package com.example.demo.service;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenadzerService {

    @Autowired
    public MenadzerRepository menadzerRepository;

    public Menadzer save(Menadzer menadzer){
        return this.menadzerRepository.save(menadzer);
    }

    public List<Menadzer> findAll(){
        return menadzerRepository.findAll();
    }

}
