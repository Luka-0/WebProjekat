package com.example.demo.service;

import com.example.demo.entity.TipKupca;
import com.example.demo.repository.TipKupcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipKupcaService {

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    public TipKupca findByIme(String imeTipaKupca){
        Optional<TipKupca> tk = tipKupcaRepository.findByIme(imeTipaKupca);

        if(tk.isPresent()){
            return tk.get();
        }else{
            return null;
        }
    }








}
