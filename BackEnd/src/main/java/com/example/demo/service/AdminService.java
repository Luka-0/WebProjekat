package com.example.demo.service;
import com.example.demo.entity.*;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.MenadzerService;
import com.example.demo.service.DostavljacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private  MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private  RestoranService restoranService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private  LokacijaService lokacijaService;

    public Menadzer saveMenadzer(Menadzer noviMenadzer){
        return this.menadzerService.save(noviMenadzer);
    }

    public Dostavljac saveDostavljac(Dostavljac dostavljac){
        return this.dostavljacService.save(dostavljac);
    }

    public Restoran saveRestoran(Restoran noviRestoran){
        return this.restoranService.save(noviRestoran);
    }

    public Lokacija saveLokacija(Lokacija novaLokacija){ return this.lokacijaService.save(novaLokacija);    }
    public List<Admin> findAll(){
        return adminRepository.findAll();
    }

    public Korisnik getByKorisnickoIme(String userName){
        return  this.korisnikService.findByKorisnickoIme(userName);
    }

    public Restoran getByLokacija(Lokacija lokacija){
        return  this.restoranService.findOneByLokacija(lokacija);
    }

    public Lokacija getLokacijaById(Long id){
        return  this.lokacijaService.getLokacijaById(id);
    }
}
