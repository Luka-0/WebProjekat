package com.example.demo.service;
import com.example.demo.entity.Dostavljac;
import com.example.demo.repository.DostavljacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DostavljacService {

    @Autowired
    public DostavljacRepository dostavljacRepository;

    public Dostavljac save(Dostavljac dostavljac){
        return this.dostavljacRepository.save(dostavljac);
    }

    public List<Dostavljac> findAll(){
        return dostavljacRepository.findAll();
    }
}
