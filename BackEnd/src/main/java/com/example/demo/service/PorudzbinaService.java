package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired KupacService kupacService;

    @Autowired
    private KomentarService komentarService;

    public List<Porudzbina> findAllByRestoranOrderById(Restoran restoran){
        return porudzbinaRepository.findAllByRestoranOrderByUuid(restoran);
    }

    public List<Porudzbina> findAll(){
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> findAllByKupac(Kupac kupac){
        return porudzbinaRepository.findAllByKupac(kupac);
    }

    public Porudzbina save(Porudzbina porudzbina){
        return porudzbinaRepository.save(porudzbina);
    }

    public List<Porudzbina> findAllByStatus(EnumStatus status){
        return porudzbinaRepository.findAllByStatus(status);
    }

    public Porudzbina findFirstByStatus(EnumStatus status, long id){
        return porudzbinaRepository.findFirstByStatusAndKupac_Id(status, id);
    }

    public Porudzbina findOneByUuid(UUID uuid){
        Optional<Porudzbina> porudzbina = porudzbinaRepository.findByUuid(uuid);

        if(porudzbina.isPresent()){
            return porudzbina.get();
        }
        return null;
    }
    public Kupac saveKupac(Porudzbina porudzbina){

        Kupac kupacPorudzbine = porudzbina.getKupac();
        double bodovi = porudzbina.getCena()/1000*133;
        kupacPorudzbine.setBrojSakupljenihBodova(kupacPorudzbine.getBrojSakupljenihBodova()+(int)bodovi);

        return this.kupacService.save(kupacPorudzbine);
    }

    public Komentar save(Komentar komentar){
        return this.komentarService.save(komentar);
    }

}
