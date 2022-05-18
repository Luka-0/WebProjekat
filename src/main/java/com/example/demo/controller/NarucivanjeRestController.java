package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class NarucivanjeRestController {

    @Autowired
    private RestoranService restoranService;

    /*
    //Kreiram porudzbinu, aj Boze pomozi
    @GetMapping("/api/kreiranje-porudzbine")
    public ResponseEntity<Porudzbina> pregledPorudzbinaKupca(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");

                Porudzbina kreiranaPorudzbina = new Porudzbina();





                return ResponseEntity.ok(kreiranaPorudzbina);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }


     */
    //-----------


    //Izlistaj artikle iz restorana
    @GetMapping("/api/pregled-artikala-resotrana/{naziv}")    //TODO mozda ovo pomeriti u ArtikalRestController?
    public ResponseEntity<Set<Artikal>> prikaziArtikleRestorana(@PathVariable(name = "naziv") String naziv){
        Restoran trazeniRestoran = restoranService.finOneByNaziv(naziv);
        if(trazeniRestoran == null){
            return new ResponseEntity(
                    "Restoran nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            Set<Artikal> trazeniArtikli = trazeniRestoran.getArtikli();
            return ResponseEntity.ok(trazeniArtikli);
        }
    }








}
