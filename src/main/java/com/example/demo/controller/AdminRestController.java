package com.example.demo.controller;

import com.example.demo.dto.NewDMDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/api/dodaj-menadzera")
    public ResponseEntity<String> dodavanjeMenadzera(@RequestBody NewDMDto newDMDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if(ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovani.getUloga() != EnumUloga.ADMIN)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Menadzer noviMenadzer = new Menadzer();

        noviMenadzer.setIme(newDMDto.getIme());
        noviMenadzer.setPrezime(newDMDto.getPrezime());
        noviMenadzer.setLozinka(newDMDto.getLozinka());
        noviMenadzer.setPol(newDMDto.getPol());
        noviMenadzer.setKorisnickoIme(newDMDto.getKorisnickoIme());
        noviMenadzer.setUloga(newDMDto.getUloga());

        if(noviMenadzer.getUloga() != EnumUloga.MENADZER)
            return new ResponseEntity("Izabrana uloga mora biti: MENADZER", HttpStatus.BAD_REQUEST);

        if(this.adminService.getByKorisnickoIme(noviMenadzer.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.adminService.saveMenadzer(noviMenadzer);

        return ResponseEntity.ok("Dodavanje Menadzera: Uspesno");

    }

    @PostMapping("/api/dodaj-dostavljaca")
    public ResponseEntity<String> dodavanjeDostavljaca(@RequestBody NewDMDto newDMDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if(ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovani.getUloga() != EnumUloga.ADMIN)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Dostavljac noviDostavljac = new Dostavljac();

        noviDostavljac.setIme(newDMDto.getIme());
        noviDostavljac.setPrezime(newDMDto.getPrezime());
        noviDostavljac.setLozinka(newDMDto.getLozinka());
        noviDostavljac.setPol(newDMDto.getPol());
        noviDostavljac.setKorisnickoIme(newDMDto.getKorisnickoIme());
        noviDostavljac.setUloga(newDMDto.getUloga());

        if(noviDostavljac.getUloga() != EnumUloga.DOSTAVLJAC)
            return new ResponseEntity("Izabrana uloga mora biti: DOSTAVLJAC", HttpStatus.BAD_REQUEST);

        if(this.adminService.getByKorisnickoIme(noviDostavljac.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime je vec zauzeto.", HttpStatus.BAD_REQUEST);

        this.adminService.saveDostavljac(noviDostavljac);

        return ResponseEntity.ok("Dodavanje Dostavljaca: Uspesno");
    }


    @PostMapping("/api/kreiraj-restoran")
    public ResponseEntity<String> dodavanjeRestorana(@RequestBody RestoranDto restoranDto, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if (ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovani.getUloga() != EnumUloga.ADMIN)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        Restoran noviRestoran = new Restoran();
        noviRestoran.setNaziv(restoranDto.getNazivRestorana());
        noviRestoran.setTipRestorana(restoranDto.getTipRestorana());

        Lokacija lokacija = this.adminService.getLokacijaById(restoranDto.getIdLokacije());

        if(this.adminService.getByLokacija(lokacija) != null){
            return new ResponseEntity("Restoran na ovoj lokaciji vec postoji.", HttpStatus.BAD_REQUEST);
        }

        noviRestoran.setLokacija(lokacija);

        this.adminService.saveRestoran(noviRestoran);

        return ResponseEntity.ok("Dodavanje novog restorana: Uspesno");
    }
}
