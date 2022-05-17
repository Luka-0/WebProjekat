package com.example.demo.controller;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @GetMapping("/api/restorani")
    public ResponseEntity<List<RestoranDto>> getRestorani(HttpSession session){

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }
        
        List<Restoran> restorani = this.restoranService.findAll();

        List<RestoranDto> dtos = new ArrayList<>();
        for(Restoran restoran : restorani){
            RestoranDto dto = new RestoranDto(restoran);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

}
