package com.example.demo.service;

import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    public List<Menadzer> findAll(){
        return menadzerRepository.findAll();
    }
}
