package com.example.demo.controller;

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

    @PostMapping("/api/dodavanjeMenadzera")
    public ResponseEntity<String> dodavanjeMenadzera(@RequestBody Menadzer noviMenadzer, HttpSession session) {
        Korisnik ulogovani = (Korisnik) session.getAttribute("korisnik");

        if(ulogovani == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovani.getUloga() != EnumUloga.ADMIN)
            return new ResponseEntity("Funkcionalnost je dostupna samo administratorima aplikacije", HttpStatus.BAD_REQUEST);

        this.adminService.saveMenadzer(noviMenadzer);

        return ResponseEntity.ok("Kreiranje Menadzera: Uspesno");

    }
    
}
