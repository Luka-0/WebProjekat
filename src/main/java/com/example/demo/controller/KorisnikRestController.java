package com.example.demo.controller;

import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.EnumUloga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.TipKupca;
import com.example.demo.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.KorisnikService;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KupacService kupacService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("/api/register")
    public String registrujKorisnika(@RequestBody RegisterDto newDto) {

        Kupac noviKupac = new Kupac();
        noviKupac.setKorisnickoIme(newDto.getKorisnickoIme());
        noviKupac.setIme(newDto.getIme());
        noviKupac.setPrezime(newDto.getPrezime());
        noviKupac.setDatumRodjenja(newDto.getDatumRodjenja());
        noviKupac.setPol(newDto.getPol());
        noviKupac.setLozinka(newDto.getLozinka());
        noviKupac.setUloga(EnumUloga.KUPAC);
        noviKupac.setBrojSakupljenihBodova(0);

        TipKupca tipKupca = new TipKupca();
        tipKupca.setIme("Novi kupac");
        tipKupca.setTrazeniBrojBodova(0);
        tipKupca.setPopust(0);

        noviKupac.setTk(tipKupca);
        this.kupacService.save(noviKupac);

        return "Korisnik" + newDto.getKorisnickoIme() + "je uspesno registrovan";
    }










}
