package com.example.demo.service;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Menadzer;
import com.example.demo.repository.DostavljacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public List<Dostavljac> findAll(){
        return dostavljacRepository.findAll();
    }
}
