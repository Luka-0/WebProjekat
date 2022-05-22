package com.example.demo.controller;

import com.example.demo.dto.IzmenaPoruceneKolicineDto;
import com.example.demo.dto.PregledArtiklaDto;
import com.example.demo.dto.PregledKorpeDto;
import com.example.demo.entity.*;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.RestoranService;
import com.example.demo.service.StavkaPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class NarucivanjeRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private StavkaPorudzbineService stavkaPorudzbineService;

    //Kreiram porudzbinu, aj Boze pomozi
    @GetMapping("/api/kreiranje-porudzbine/{id}")
    public ResponseEntity<String> kreiranjePorudzbine(@PathVariable(name = "id") long idRestorana, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");

                Restoran trazeniRestoran = restoranService.findOneById(idRestorana);
                if(trazeniRestoran == null)
                    return new ResponseEntity(
                            "Restoran nije pronadjen",
                            HttpStatus.NOT_FOUND);

                if(trazeniRestoran.getStatusRestorana() == EnumStatusRestorana.NE_RADI){
                    return new ResponseEntity("Restoran ne radi", HttpStatus.FORBIDDEN);
                }else{
                    Porudzbina kreiranaPorudzbina = new Porudzbina();
                    kreiranaPorudzbina.setStatus(EnumStatus.kreira_se);
                    kreiranaPorudzbina.setKupac(ulogovaniKupac);
                    porudzbinaService.save(kreiranaPorudzbina);
                    return ResponseEntity.ok("Porudzbina je kreirana!\n");
                }


            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Izlistaj artikle iz restorana
    @GetMapping("/api/pregled-artikala-resotrana/{id}")    //TODO mozda ovo pomeriti u ArtikalRestController?
    public ResponseEntity<Set<Artikal>> prikaziArtikleRestorana(@PathVariable(name = "id") long id){
        Restoran trazeniRestoran = restoranService.findOneById(id);
        if(trazeniRestoran == null){
            return new ResponseEntity(
                    "Restoran nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            Set<Artikal> trazeniArtikli = trazeniRestoran.getArtikli();
            return ResponseEntity.ok(trazeniArtikli);
        }
    }

    //Dodavanje stavke u korpu
    @PostMapping("/api/dodaj-stavku")
    public ResponseEntity<String> dodajStavkuPorudzbine(@RequestBody StavkaPorudzbine novaStavka, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());

                korpa.dodajStavku(novaStavka);
                stavkaPorudzbineService.save(novaStavka);
                return ResponseEntity.ok("Stavka " + novaStavka.toString() + " je uspesno dodata!");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Brisanje stavke iz korpe
    @DeleteMapping("/api/izbrisi-stavku/{id}")
    public ResponseEntity<String> izbrisiStavkuPorudzbine(@PathVariable(name = "id") long stavkaId, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());

                StavkaPorudzbine stavkaKojaSeUklanja = stavkaPorudzbineService.getById(stavkaId);
                korpa.ukloniStavku(stavkaKojaSeUklanja);
                stavkaPorudzbineService.deleteById(stavkaId);

                return ResponseEntity.ok("Stavka " + stavkaKojaSeUklanja.toString() + " uspesno uklonjena");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //TODO dodaj metode za smanjivanje i povecavanje porucene kolicine
    //Izmena kolicine
    @PutMapping("/api/izmeni-kolicinu")
    public ResponseEntity<String> izmeniKolicinu(@RequestBody IzmenaPoruceneKolicineDto izmenaDto){
            StavkaPorudzbine stavkaKojaSeAzurira = stavkaPorudzbineService.getById(izmenaDto.getIdStavkeKojaSeMenja());

            if(izmenaDto.getNovaPorucenaKolicina() > 0){
                stavkaKojaSeAzurira.setPorucenaKolicina(izmenaDto.getNovaPorucenaKolicina());
                stavkaPorudzbineService.save(stavkaKojaSeAzurira);
                return ResponseEntity.ok("Stavka " + stavkaKojaSeAzurira.toString() + " uspesno azurirana");
            }else{
                return new ResponseEntity(
                        "Kolicina mora biti veca od nule",
                        HttpStatus.BAD_REQUEST);
            }

    }

    //Pregled stvari za porucivanje
    @GetMapping("/api/pregled-korpe")
    public ResponseEntity<PregledKorpeDto> pregledKorpe(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());
                korpa.setCena(korpa.izracunajCenu());

                List<PregledArtiklaDto> listaP = new ArrayList<>();
                PregledArtiklaDto pregledArtikla;
                PregledKorpeDto pregledKorpe = new PregledKorpeDto();

                for(StavkaPorudzbine st: korpa.getStavkePorudzbine()){
                    pregledArtikla = new PregledArtiklaDto();
                    pregledArtikla.setNazivArtikla(st.getArtikal().getNaziv());
                    pregledArtikla.setCenaArtikla(st.getArtikal().getCena());
                    pregledArtikla.setPorucenaKolicina(st.getPorucenaKolicina());
                    pregledArtikla.setKolicinaArtikla(st.getArtikal().getKolicina());

                    listaP.add(pregledArtikla);
                }
                pregledKorpe.setUkupnaCenaPorudzbine(korpa.izracunajCenu());
                pregledKorpe.setPregledArtikala(listaP);

                return ResponseEntity.ok(pregledKorpe);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Porucivanje
    @PutMapping("/api/poruci/{id}")
    public ResponseEntity<String> porucivanje(@PathVariable(name = "id") long idRestorana, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
                Kupac ulogovaniKupac = (Kupac) session.getAttribute("korisnik");
                Porudzbina korpa = porudzbinaService.findFirstByStatus(EnumStatus.kreira_se, ulogovaniKupac.getId());
                korpa.setStatus(EnumStatus.Obrada);
                korpa.setCena(korpa.izracunajCenu());

                Restoran trazeniRestoran = restoranService.findOneById(idRestorana);
                korpa.setRestoran(trazeniRestoran);

                porudzbinaService.save(korpa);
                return ResponseEntity.ok("Kraj narucivanja. Porudzbina se obradjuje!");
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije kupac",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }





}
