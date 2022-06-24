package com.example.demo.controller;

import com.example.demo.dto.PregledStavkePorudzbineDto;
import com.example.demo.dto.PregledKorpeDto;
import com.example.demo.dto.noviKomentarDto;
import com.example.demo.entity.*;
import com.example.demo.service.KomentarService;
import com.example.demo.service.KupacService;
import com.example.demo.service.PorudzbinaService;
import com.example.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class PorudzbinaRestController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private RestoranService restoranService;



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
    @PostMapping("/api/kupac-pregled-porudzbina/{uuid}")
    public ResponseEntity<String> ostaviKomentar(@PathVariable(name = "uuid") String uuidPorudzbine, @RequestBody noviKomentarDto dto, HttpSession session) {

        UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if(uk == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(uk.getUloga() != EnumUloga.KUPAC)
            return new ResponseEntity("Funkcionalnost je dostupna samo kupcima.", HttpStatus.BAD_REQUEST);

        Porudzbina porudzbina = new Porudzbina();
        porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

        if (porudzbina == null) {
            return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.BAD_REQUEST);
        }
        if (porudzbina.getStatus() == EnumStatus.dostavljena) {
            Kupac kupac = (Kupac)uk;
            Komentar noviKomentar = new Komentar(kupac, porudzbina.getRestoran(), dto.getTekstKomentara(), dto.getOcena());

            this.porudzbinaService.save(noviKomentar);
        }
        else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Uspesno dodat komentar!");
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
                    if(p.getStatus() == EnumStatus.ceka_dostavljaca || p.getStatus() == EnumStatus.u_transportu){
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
                        "Ulogovani korisnik nije menadzer.",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    // ---------------- NARUCIVANJE -------------------

    //Kreiranje porudzbine
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

    //Pregled porucenih stvari
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

                List<PregledStavkePorudzbineDto> listaP = new ArrayList<>();
                PregledStavkePorudzbineDto pregledArtikla;
                PregledKorpeDto pregledKorpe = new PregledKorpeDto();

                for(StavkaPorudzbine st: korpa.getStavkePorudzbine()){
                    pregledArtikla = new PregledStavkePorudzbineDto();
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

    @PutMapping("/api/menadzer-pregled-porudzbina/{uuid}")
    public ResponseEntity<String> menadzerMenjaStatus(@PathVariable(name = "uuid") String uuidPorudzbine, HttpSession session) {

        UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.MENADZER) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

        if (porudzbina == null) {
            return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.BAD_REQUEST);
        }

        if (porudzbina.getStatus() == EnumStatus.ceka_dostavljaca) {
            return new ResponseEntity("Nije moguce promeniti status porudzbine - porudzbina ceka dostavljaca.", HttpStatus.BAD_REQUEST);
        }

        if (porudzbina.getStatus() == EnumStatus.u_transportu) {
            return new ResponseEntity("Nije moguce promeniti status porudzbine - porudzbina je u transportu.", HttpStatus.BAD_REQUEST);
        }

        if (porudzbina.getStatus() == EnumStatus.Obrada || porudzbina.getStatus() == EnumStatus.u_pripremi) {

            if (porudzbina.getStatus() == EnumStatus.Obrada) {

                porudzbina.setStatus(EnumStatus.u_pripremi);
                //this.porudzbinaService.save(porudzbina);
            }
            else {

                porudzbina.setStatus(EnumStatus.ceka_dostavljaca);
                // this.porudzbinaService.save(porudzbina);
            }
            this.porudzbinaService.save(porudzbina);
        }
        return ResponseEntity.ok("Uspesno promenjen status porudzbine u: "+ porudzbina.getStatus().name()+".");
    }


    @PutMapping("/api/dostavljac-pregled-porudzbina/{uuid}")
    public ResponseEntity<String> dostavljacMenjaStatus(@PathVariable(name = "uuid") String uuidPorudzbine, HttpSession session) {

        UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.DOSTAVLJAC) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }

        Porudzbina porudzbina = new Porudzbina();
        porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

        if (porudzbina == null) {
            return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.BAD_REQUEST);
        }

        if (porudzbina.getStatus() == EnumStatus.ceka_dostavljaca || porudzbina.getStatus() == EnumStatus.u_transportu) {

            if (porudzbina.getStatus() == EnumStatus.ceka_dostavljaca) {
                porudzbina.setStatus(EnumStatus.u_transportu);
            }
            else {
                porudzbina.setStatus(EnumStatus.dostavljena);

                /*  --prebaceno u PorudzbinaService
                    Kupac kupacPorudzbine = porudzbina.getKupac();
                    double bodovi = porudzbina.getCena()/1000*133;
                    kupacPorudzbine.setBrojSakupljenihBodova(kupacPorudzbine.getBrojSakupljenihBodova()+(int)bodovi);
                */
                this.porudzbinaService.saveKupac(porudzbina);
            }
            this.porudzbinaService.save(porudzbina);
        }
        return ResponseEntity.ok("Uspesno promenjen status porudzbine u: "+ porudzbina.getStatus().name()+".");
    }

    @PutMapping("/api/kupac-pregled-porudzbina/otkazi/{uuid}")
    public ResponseEntity<String> otkaziPorudzbinu(@PathVariable(name = "uuid") String uuidPorudzbine, HttpSession session) {

        UUID uuid_porudzbine = UUID.fromString(uuidPorudzbine);

        Korisnik uk = (Korisnik) session.getAttribute("korisnik");

        if (uk == null) {
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        }

        if (uk.getUloga() != EnumUloga.KUPAC) {
            return new ResponseEntity("Nemate prava pristupa.", HttpStatus.UNAUTHORIZED);
        }
        Kupac kupac = (Kupac) session.getAttribute("korisnik");

        Porudzbina porudzbina = new Porudzbina();
        porudzbina = this.porudzbinaService.findOneByUuid(uuid_porudzbine);

        //zbog testiranja u postaman-u
        if (porudzbina == null) {
            return new ResponseEntity("Porudzbina nije pronadjena.", HttpStatus.NOT_FOUND);
        }

        if(porudzbina.getStatus() != EnumStatus.Obrada){
            return new ResponseEntity("Nije moguce otazati porudzbinu.\nStatus Vase oprudzbine je: U PRIPREMI.", HttpStatus.BAD_REQUEST);
        }

        porudzbina.setStatus(EnumStatus.otkazana);

        this.porudzbinaService.save(porudzbina);

        return ResponseEntity.ok("Porudzbina je otkazana.");
    }
}
