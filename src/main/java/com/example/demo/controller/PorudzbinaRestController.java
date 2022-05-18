package com.example.demo.controller;

import com.example.demo.dto.MenadzerovPregledDto;
import com.example.demo.entity.*;
import com.example.demo.service.KupacService;
import com.example.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class PorudzbinaRestController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;


    //Pregled porudzbina kupaca
    @GetMapping("/api/kupac-pregled-porudzbina")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbinaKupca(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                List<Porudzbina> listaKupcevihPorudzbina = porudzbinaService.findAllByKupac(ulogovaniKupac);
                return ResponseEntity.ok(listaKupcevihPorudzbina);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Pregled porudzbina dostavljaca
    @GetMapping("/api/dostavljac-pregled-porudzbina")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbinaDostavljaca(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.DOSTAVLJAC){
                Dostavljac ulogovaniDostavljac = (Dostavljac) session.getAttribute("korisnik");

                List<Porudzbina> listaVidljivihPorudzbina = new ArrayList<>();  //lista koju vracamo

                List<Porudzbina> listaSvihPorudzbina = porudzbinaService.findAll();     //sve porudzbine
                Set<Porudzbina> listaDostavljacevihPorudzbina =  ulogovaniDostavljac.getPorudzbine();   //porudzbine koje raznosi ulogovani dostavljac

                //Trazenje porudzbina koje cekaju dostavu
                for(Porudzbina p: listaSvihPorudzbina){
                    if(p.getStatus() == EnumStatus.ceka_dostavljaca){
                        listaVidljivihPorudzbina.add(p);
                    }
                }

                //Dodavanje porudzbina dostavljaca
                for(Porudzbina p: listaDostavljacevihPorudzbina){
                    listaVidljivihPorudzbina.add(p);
                }

                return ResponseEntity.ok(listaVidljivihPorudzbina);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije dostavljac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Menadzerov pregled porudzbina njegovog restorana
    @GetMapping("/api/menadzer-pregled-porudzbina")
    public ResponseEntity<List<Porudzbina>> pregledPorudzbinaMenadzerovogRestorana(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.MENADZER){
                Menadzer ulogovaniMenadzer = (Menadzer) session.getAttribute("korisnik");

                List<Porudzbina> porudzbineMenadzerovogRestorana = porudzbinaService.findAllByRestoranOrderById(ulogovaniMenadzer.getRestoran());
                return ResponseEntity.ok(porudzbineMenadzerovogRestorana);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije dostavljac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }















}
