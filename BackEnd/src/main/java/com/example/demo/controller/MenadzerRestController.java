package com.example.demo.controller;

import com.example.demo.dto.RestoranMenadzerDto;
import com.example.demo.entity.EnumUloga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import com.example.demo.entity.Restoran;
import com.example.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    @PutMapping("/api/admin/postavljanje-manadzera")
    public ResponseEntity<Menadzer> addRestoranMenadzer(@RequestBody RestoranMenadzerDto dto, HttpSession session) {

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(uk.getUloga() != EnumUloga.ADMIN)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        //pronalazenje restorana po izabranom id-u
          Restoran restoran = new Restoran();
          restoran = this.menadzerService.findRestoranById(dto.getIdRestorana());

        if(restoran == null){
            return new ResponseEntity("Izabrani restoran ne postoji.", HttpStatus.BAD_REQUEST);
        }

        if(this.menadzerService.findByRestoran(restoran) != null){
            return new ResponseEntity("Izabrani restoran vec ima menadzera.", HttpStatus.BAD_REQUEST);
        }

        //pronalazenje menadzera po izabranom korisnickom imenu
        Menadzer menadzer = new Menadzer();
        menadzer = this.menadzerService.findByKorisnickoIme(dto.getKorisnickoIme());

        if(menadzer == null || menadzer.getUloga() != EnumUloga.MENADZER){
            return new ResponseEntity("Ne postoji menadzer sa izabranim korisnickim imenom.", HttpStatus.BAD_REQUEST);
        }

        menadzer.setRestoran(restoran);
        final Menadzer updatedMenadzer = menadzerService.save(menadzer);

        return ResponseEntity.ok(updatedMenadzer);
    }
}
