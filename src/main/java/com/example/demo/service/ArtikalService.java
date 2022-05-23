package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.Restoran;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal findOneById(long id){
        Optional<Artikal> a = artikalRepository.findById(id);

        if(a.isPresent()){
            return a.get();
        }
        return null;
    }
}
