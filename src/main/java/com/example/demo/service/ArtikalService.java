package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal save(Artikal artikal){
        return this.artikalRepository.save(artikal);
    }

    public void delete(Artikal artikal) {   artikalRepository.delete(artikal);  }
}
