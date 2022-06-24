package com.example.demo.controller;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @GetMapping("/api/restorani")
    public ResponseEntity<List<PretragaDto>> getRestorani(HttpSession session){

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }
        
        List<Restoran> restorani = this.restoranService.findAll();

        List<PretragaDto> dtos = new ArrayList<>();
        for(Restoran restoran : restorani){
            PretragaDto dto = new PretragaDto(restoran);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);

    }

    @PostMapping("/api/pretraga")
    public ResponseEntity<List<PretragaDto>> pretraziRestorane(@RequestBody PretragaDto dtoPretraga, HttpSession session) {

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        List<Restoran> restorani = restoranService.findAll();

        List<PretragaDto> rezultatPretrage = new ArrayList<>();

        if(dtoPretraga.getNaziv() != null) {

            for (Restoran restoran : restorani) {

                // indeOf zbog preciznije pretrage - provera da li postoji restoran ciji naziv sadrzi substring naziva
                // koji korisnik unosi prilikom pretrage
                // pretvaranje oba naziva u lowercase kako pretraga ne bi bila case-sensitive

                if (restoran.getNaziv().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getNaziv().toLowerCase(Locale.ROOT)) != -1) {

                    PretragaDto r = new PretragaDto((restoran));

                    boolean postoji = false;
                // rezliciti kriterijumi pretrage mogu odgovarati istom restoranu
                // ako je rezultat pretrage, tj. restoran vec u listi ne dodaje se ponovo
                    if(!rezultatPretrage.isEmpty()){
                        for(PretragaDto dto : rezultatPretrage){
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

                    PretragaDto r = new PretragaDto((restoran));

                    boolean postoji = false;

                    if(!rezultatPretrage.isEmpty()){
                        for(PretragaDto dto : rezultatPretrage){
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

                    PretragaDto r = new PretragaDto((restoran));

                    boolean postoji = false;

                    if(!rezultatPretrage.isEmpty()){
                        for(PretragaDto dto : rezultatPretrage){
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
    @GetMapping("/api/pretraga/{id}")
    public ResponseEntity<PrikazRestoranaDto> getRestoranByNaziv(@PathVariable(name = "id") long id, HttpSession session){

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        //pronalazenje izabranog restorana
        Restoran restoran = restoranService.findById(id);

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

    @PostMapping("/api/dodaj-novi-artikal")
    public ResponseEntity<Restoran> addArtikal(@RequestParam(name="image", required=true) MultipartFile multipartFile,
                                                @RequestParam(name="naziv", required=true) String naziv,
                                                @RequestParam(name="cena", required=true) String cena,
                                                @RequestParam(name="tip", required=true) String tip,
                                                @RequestParam(name="kolicina", required=false) String kolicina,
                                                @RequestParam(name="opis", required=false) String opis,
                                                HttpSession session)
    {
        double kolicinaArtikla = 0;
        if(kolicina!= null && !kolicina.isEmpty()){
            kolicinaArtikla = Double.parseDouble(kolicina);
        }

        double cenaArtikla = 0;
        if(cena!= null && !cena.isEmpty()){
            cenaArtikla = Double.parseDouble(cena);
        }
        NewArtikalDto dto = new NewArtikalDto(naziv, cenaArtikla, tip, kolicinaArtikla, opis);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.MENADZER) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }

        if(dto.getNaziv().isEmpty()  || dto.getTip().isEmpty() || dto.getCena() <= 0 || multipartFile.isEmpty()){
            return new ResponseEntity("Obavezni podaci za unos: NAZIV, TIP, CENA, SLIKA", HttpStatus.BAD_REQUEST);
        }

        dto.getTip().toUpperCase(Locale.ROOT);
        if(!dto.getTip().equals("JELO") && !dto.getTip().equals("PICE")) {
            return new ResponseEntity("Tip artikla moze biti: JELO ili PICE", HttpStatus.BAD_REQUEST);
        }

        Artikal noviArtikal = new Artikal();

        noviArtikal.setNaziv(dto.getNaziv());
        noviArtikal.setCena(dto.getCena());
        noviArtikal.setOpis(dto.getOpis());
        noviArtikal.setTip(EnumTip.valueOf(dto.getTip()));
        noviArtikal.setKolicina(dto.getKolicina());

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        noviArtikal.setPhoto(fileName);


        this.restoranService.saveArtikal(noviArtikal);
        this.restoranService.uploadToLocal(multipartFile);

        Menadzer menadzer = (Menadzer) uk;
        Restoran restoran = menadzer.getRestoran();

        restoran.getArtikli().add(noviArtikal);

        this.restoranService.save(restoran);
        return ResponseEntity.ok(restoran);
    }

    @DeleteMapping("/api/brisanje-artikla/{id}")
    public ResponseEntity<String> deleteArtikal(@PathVariable(name = "id") Long id, HttpSession session) {

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.MENADZER) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }

        Menadzer menadzer = (Menadzer) uk;
        Restoran restoran = menadzer.getRestoran();

        for(Artikal artikal : restoran.getArtikli()){

            if(artikal.getId() == id){
                restoran.getArtikli().remove(artikal);

                //brisanje iz restorana
                this.restoranService.save(restoran);
                //brisanje iz baze
                //this.restoranService.deleteArtikal(artikal);

                return ResponseEntity.ok("Uspesno obrisan artikal!");
            }
        }
        return ResponseEntity.ok("Nepostojeci artikal!");
    }

    @PutMapping("/api/artikal-update/{id}")
    public ResponseEntity<String>  updateArtikal(@PathVariable(name = "id") Long id,
                                                 @RequestParam(name="image", required = false) MultipartFile multipartFile,
                                                 @RequestParam(name="naziv", required = false) String naziv,
                                                 @RequestParam(name="cena", required = false) String cena,
                                                 @RequestParam(name="tip", required = false) String tip,
                                                 @RequestParam(name="kolicina", required = false) String kolicina,
                                                 @RequestParam(name="opis", required = false) String opis,
                                                 HttpSession session)
    {

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.MENADZER) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }

        Menadzer menadzer = (Menadzer) uk;

        //da li artikal postoji u menadzerovom restoranu
        //menadzer moze menjati podatke o artiklu/ima, samo iz svog resorana
        boolean postoji = false;

        for(Artikal artikal : menadzer.getRestoran().getArtikli()){
            if(artikal.getId() == id){
                postoji = true;
                break;
            }
        }

        if(!postoji){
            return new ResponseEntity("Nepostojeci srtikal!", HttpStatus.BAD_REQUEST);
        }

        Artikal toBeUpdated = new Artikal();
        toBeUpdated = this.restoranService.findArtikalById(id);

        if(tip != null && !tip.isEmpty()){
            String tipArtikla = tip;

            tipArtikla.toUpperCase(Locale.ROOT);
            if(!tipArtikla.equals("JELO") && !tipArtikla.equals("PICE")) {
                return new ResponseEntity("Tip artikla mora biti: JELO ili PICE", HttpStatus.BAD_REQUEST);
            }
            toBeUpdated.setTip(EnumTip.valueOf(tipArtikla));
        }

        if(naziv != null && !naziv.isEmpty()){
            toBeUpdated.setNaziv(naziv);
        }

        if(cena != null && !cena.isEmpty()){
            double cenaArtikla = Double.parseDouble(cena);
            toBeUpdated.setCena(cenaArtikla);
        }

        if(opis != null && !opis.isEmpty()){
            toBeUpdated.setOpis(opis);
        }

        if(kolicina != null && !kolicina.isEmpty()){
            double kolicinaArtikla = Double.parseDouble(kolicina);
            toBeUpdated.setKolicina(kolicinaArtikla);
        }

        if(multipartFile != null && !multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            toBeUpdated.setPhoto(fileName);
            this.restoranService.uploadToLocal(multipartFile);
        }

        this.restoranService.saveArtikal(toBeUpdated);

        return ResponseEntity.ok("Uspesno promnjeni podaci o artiklu!");
    }
}
