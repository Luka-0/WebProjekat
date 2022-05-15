package com.example.demo.service;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenadzerService {

    @Autowired
    public MenadzerRepository menadzerRepository;

    public Menadzer save(Menadzer menadzer){
        return menadzerRepository.save(menadzer);
    }

}
