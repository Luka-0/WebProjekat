package com.example.demo.service;

import com.example.demo.entity.StavkaPorudzbine;
import com.example.demo.repository.StavkaPorudzbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StavkaPorudzbineService {
    @Autowired
    private StavkaPorudzbineRepository stavkaPorudzbineRepository;

    public StavkaPorudzbine getById(long id){
        return stavkaPorudzbineRepository.getById(id);
    }
    public StavkaPorudzbine save(StavkaPorudzbine stavka){return  stavkaPorudzbineRepository.save(stavka);}
    public void deleteById(long id){
        stavkaPorudzbineRepository.deleteById(id);
    }
}
