package com.example.demo.controller;

import com.example.demo.dto.NoviArtikalDto;
import com.example.demo.dto.PretragaDto;
import com.example.demo.dto.PrikazListeArtikalaDto;
import com.example.demo.entity.Artikal;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.service.ArtikalService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ArtikalRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    //Prikaz artikala restorana
    @GetMapping("/api/pregled-artikala-restorana/{id}")
    public ResponseEntity<List<PrikazListeArtikalaDto>> prikaziArtikleRestorana(@PathVariable(name = "id") long id){
        Restoran trazeniRestoran = restoranService.findOneById(id);
        if(trazeniRestoran == null){
            return new ResponseEntity(
                    "Restoran nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            Set<Artikal> trazeniArtikli = trazeniRestoran.getArtikli();

            List<PrikazListeArtikalaDto> listaArtikalaDto = new ArrayList<>();
            PrikazListeArtikalaDto temp;
            for(Artikal a: trazeniArtikli){
                temp = new PrikazListeArtikalaDto();
                temp.setNaziv(a.getNaziv());
                temp.setCena(a.getCena());
                temp.setKolicina(a.getKolicina());
                temp.setOpis(a.getOpis());

                listaArtikalaDto.add(temp);
            }

            return ResponseEntity.ok(listaArtikalaDto);
        }
    }

    //Prikaz artikla
    @GetMapping("/api/prikaz-artikla/{id}")
    public ResponseEntity<Artikal> prikazArtikla(@PathVariable(name = "id") long id){
        Artikal trazeniArtikal = artikalService.findById(id);

        if(trazeniArtikal == null){
            return new ResponseEntity(
                    "Artikal nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(trazeniArtikal);
    }


    @PostMapping("/api/dodavanje-novoga-artikla")
    public ResponseEntity<Artikal> dodajArtikl(@RequestBody NoviArtikalDto noviDto, HttpSession session){

        Menadzer uk = (Menadzer) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        Restoran restoran = uk.getRestoran();
        Artikal noviArtikal = new Artikal();

        noviArtikal.setTip(noviDto.getTip());
        noviArtikal.setCena(noviDto.getCena());
        noviArtikal.setOpis(noviDto.getOpis());
        noviArtikal.setNaziv(noviDto.getNaziv());
        noviArtikal.setKolicina(noviDto.getKolicina());
        noviArtikal.setPhoto(noviDto.getUrl());


        restoran.getArtikli().add(noviArtikal);
        restoranService.saveArtikal(noviArtikal);
        this.restoranService.save(restoran);
        return ResponseEntity.ok(noviArtikal);

    }












}
