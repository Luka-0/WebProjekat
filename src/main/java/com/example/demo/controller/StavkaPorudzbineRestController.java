package com.example.demo.controller;

import com.example.demo.dto.IzmenaPoruceneKolicineDto;
import com.example.demo.entity.*;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.StavkaPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class StavkaPorudzbineRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private StavkaPorudzbineService stavkaPorudzbineService;

    //Dodavanje stavke u korpu
    @PostMapping("/api/dodaj-stavku")
    public ResponseEntity<String> dodajStavkuPorudzbine(@RequestBody StavkaPorudzbine novaStavka, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());

                korpa.dodajStavku(novaStavka);
                stavkaPorudzbineService.save(novaStavka);
                return ResponseEntity.ok("Stavka " + novaStavka.toString() + " je uspesno dodata!");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Brisanje stavke
    @DeleteMapping("/api/izbrisi-stavku/{id}")
    public ResponseEntity<String> izbrisiStavkuPorudzbine(@PathVariable(name = "id") long stavkaId, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());

                StavkaPorudzbine stavkaKojaSeUklanja = stavkaPorudzbineService.getById(stavkaId);
                korpa.ukloniStavku(stavkaKojaSeUklanja);
                stavkaPorudzbineService.deleteById(stavkaId);

                return ResponseEntity.ok("Stavka " + stavkaKojaSeUklanja.toString() + " uspesno uklonjena");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Izmena kolicine
    @PutMapping("/api/izmeni-kolicinu")
    public ResponseEntity<String> izmeniKolicinu(@RequestBody IzmenaPoruceneKolicineDto izmenaDto){
        StavkaPorudzbine stavkaKojaSeAzurira = stavkaPorudzbineService.getById(izmenaDto.getIdStavkeKojaSeMenja());

        if(izmenaDto.getNovaPorucenaKolicina() > 0){
            stavkaKojaSeAzurira.setPorucenaKolicina(izmenaDto.getNovaPorucenaKolicina());
            stavkaPorudzbineService.save(stavkaKojaSeAzurira);
            return ResponseEntity.ok("Stavka " + stavkaKojaSeAzurira.toString() + " uspesno azurirana");
        }else{
            return new ResponseEntity(
                    "Kolicina mora biti veca od nule",
                    HttpStatus.BAD_REQUEST);
        }

    }

















}
