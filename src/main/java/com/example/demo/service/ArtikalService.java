package com.example.demo.service;

import com.example.demo.entity.Artikal;
import com.example.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public Artikal save(Artikal artikal){
        return this.artikalRepository.save(artikal);
    }

    public void delete(Artikal artikal) {   artikalRepository.delete(artikal);  }

    public Artikal findById(long id){
        Optional<Artikal> artikal = artikalRepository.findById(id);

        if(artikal.isPresent()){
            return artikal.get();
        }
        return null;
    }
}
