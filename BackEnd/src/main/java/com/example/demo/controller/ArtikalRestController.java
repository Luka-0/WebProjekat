package com.example.demo.controller;

import com.example.demo.dto.PrikazListeArtikalaDto;
import com.example.demo.entity.Artikal;
import com.example.demo.entity.Restoran;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ArtikalRestController {

    @Autowired
    private RestoranService restoranService;

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

}
