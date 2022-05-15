package com.example.demo.service;

import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<Porudzbina> findAllByRestoranOrderById(Restoran restoran){
        return porudzbinaRepository.findAllByRestoranOrderByUuid(restoran);
    }

}
