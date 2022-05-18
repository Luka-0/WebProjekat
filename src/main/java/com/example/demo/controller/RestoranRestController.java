package com.example.demo.controller;
import com.example.demo.dto.ArtikalPrikazDto;
import com.example.demo.dto.KomentarRestoranaDto;
import com.example.demo.dto.PrikazRestoranaDto;
import com.example.demo.dto.RestoranDto;
import com.example.demo.entity.*;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @PostMapping("/api/pretraga")
    public ResponseEntity<List<RestoranDto>> pretraziRestorane(@RequestBody RestoranDto dtoPretraga, HttpSession session) {

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        List<Restoran> restorani = restoranService.findAll();

        List<RestoranDto> rezultatPretrage = new ArrayList<>();

        if(dtoPretraga.getNaziv() != null) {

            for (Restoran restoran : restorani) {

                // indeOf zbog preciznije pretrage - provera da li postoji restoran ciji naziv sadrzi substring naziva
                // koji korisnik unosi prilikom pretrage
                // pretvaranje oba naziva u lowercase kako pretraga ne bi bila case-sensitive

                if (restoran.getNaziv().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getNaziv().toLowerCase(Locale.ROOT)) != -1) {

                    RestoranDto r = new RestoranDto((restoran));

                    boolean postoji = false;
                // rezliciti kriterijumi pretrage mogu odgovarati istom restoranu
                // ako je rezultat pretrage, tj. restoran vec u listi ne dodaje se ponovo
                    if(!rezultatPretrage.isEmpty()){
                        for(RestoranDto dto : rezultatPretrage){
                //uporedjivanje adresa - jer mogu postojati dva restorana sa istim nazivom, ali ne na istoj adresi
                            if(dto.getAdresaLokacije().equals(r.getAdresaLokacije())){
                                postoji = true;
                                break;
                            }
                        }
                    }

                    if(postoji == false){
                        rezultatPretrage.add(r);
                    }
                }
            }
        }

        if(dtoPretraga.getAdresaLokacije() != null) {

            for (Restoran restoran : restorani) {

                if (restoran.getLokacija().getAdresa().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getAdresaLokacije().toLowerCase()) != -1) {

                    RestoranDto r = new RestoranDto((restoran));

                    boolean postoji = false;

                    if(!rezultatPretrage.isEmpty()){
                        for(RestoranDto dto : rezultatPretrage){
                            if(dto.getAdresaLokacije().equals(r.getAdresaLokacije())){
                                postoji = true;
                                break;
                            }
                        }
                    }

                    if(postoji == false){
                        rezultatPretrage.add(r);
                    }
                }
            }
        }

        if(dtoPretraga.getTipRestorana() != null) {
            for (Restoran restoran : restorani) {
                if (restoran.getTipRestorana().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getTipRestorana().toLowerCase(Locale.ROOT)) != -1) {

                    RestoranDto r = new RestoranDto((restoran));

                    boolean postoji = false;

                    if(!rezultatPretrage.isEmpty()){
                        for(RestoranDto dto : rezultatPretrage){
                            if(dto.getAdresaLokacije().equals(r.getAdresaLokacije())){
                                postoji = true;
                                break;
                            }
                        }
                    }

                    if(postoji == false){
                        rezultatPretrage.add(r);
                    }
                }
            }
        }

        if(rezultatPretrage.size() == 0)
        {
            return new ResponseEntity("Nema rezultata pretrage.", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(rezultatPretrage);
    }
    @GetMapping("/api/pretraga/naziv/{naziv}")
    public ResponseEntity<PrikazRestoranaDto> getRestoranByNaziv(@PathVariable(name = "naziv") String naziv){

        //pronalazenje izabranog restorana
        Restoran restoran = restoranService.findByNaziv(naziv);

        //dobavljanje svih komentara
        List<Komentar> komentariRestorana = restoranService.findAllComments(restoran);

        List<KomentarRestoranaDto> komentariZaPrikaz = new ArrayList<>();

        //ucitavanje svih artikala
        Set<Artikal> artikliRestorana = new HashSet<Artikal>();
        artikliRestorana = restoran.getArtikli();

        Set<ArtikalPrikazDto> artikliPrikaza = new HashSet<ArtikalPrikazDto>();

        for(Komentar komentar : komentariRestorana){
            KomentarRestoranaDto dto = new KomentarRestoranaDto(komentar);
            komentariZaPrikaz.add(dto);
        }

        PrikazRestoranaDto prikazDto = new PrikazRestoranaDto();

        prikazDto.setNaziv(restoran.getNaziv());
        prikazDto.setLokacija(restoran.getLokacija());
        prikazDto.setTipRestorana(restoran.getTipRestorana());
        prikazDto.setKomentari(komentariZaPrikaz);

        double prosecnaOcena = 0;
        for(Komentar komentar :  komentariRestorana){
            prosecnaOcena += komentar.getOcena();
        }

        if(komentariRestorana.size() > 0) {
            prosecnaOcena = prosecnaOcena / komentariRestorana.size();
        }

        prikazDto.setProsecnaOcena(prosecnaOcena);

        for(Artikal artikal : artikliRestorana){
            ArtikalPrikazDto dto = new ArtikalPrikazDto(artikal);
            artikliPrikaza.add(dto);
        }

        prikazDto.setArtikli(artikliPrikaza);
        prikazDto.setStatus(restoran.getStatusRestorana().toString());

        return ResponseEntity.ok(prikazDto);
    }
}
