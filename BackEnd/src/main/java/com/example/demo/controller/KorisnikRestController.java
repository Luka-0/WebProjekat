package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @Autowired
    private TipKupcaService tipKupcaService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    //Registracija korisnika
    @PostMapping("/api/register")
    public String registrujKorisnika(@RequestBody RegisterDto newDto) {

        Kupac noviKupac = new Kupac();
        noviKupac.setKorisnickoIme(newDto.getKorisnickoIme());
        noviKupac.setIme(newDto.getIme());
        noviKupac.setPrezime(newDto.getPrezime());
        noviKupac.setDatumRodjenja(newDto.getDatumRodjenja());
        noviKupac.setPol(newDto.getPol());
        noviKupac.setLozinka(newDto.getLozinka());
        noviKupac.setUloga(EnumUloga.KUPAC);
        noviKupac.setBrojSakupljenihBodova(0);

        TipKupca tipKupca = tipKupcaService.findByIme("Novi kupac");
        if(tipKupca == null){
            tipKupca.setIme("Novi kupac");
            tipKupca.setTrazeniBrojBodova(0);
            tipKupca.setPopust(0);
        }

        noviKupac.setTk(tipKupca);
        this.kupacService.save(noviKupac);

        return "Korisnik" + newDto.getKorisnickoIme() + "je uspesno registrovan";
    }

    //LogIn korisnika
    @PostMapping("api/login")
    public ResponseEntity<Korisnik> login(@RequestBody LoginDto loginDto, HttpSession session){
        //provera podataka
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Uneti podaci su neispravni", HttpStatus.BAD_REQUEST);

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Korisnik ne postoji!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", ulogovaniKorisnik);
//        return ResponseEntity.ok("Korisnik " + ulogovaniKorisnik.getKorisnickoIme() + " je uspesno ulogovan!");
        return ResponseEntity.ok(ulogovaniKorisnik);
    }

    //LogOut korisnika
    @PostMapping("api/logout")
    public ResponseEntity<String> logOut(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste se ulogovali", HttpStatus.FORBIDDEN);

        if(ulogovaniKorisnik.getUloga() == EnumUloga.KUPAC){
            List<Porudzbina> korpe = porudzbinaService.findAllByStatusAndKupacid(EnumStatus.kreira_se, ulogovaniKorisnik.getId());
            for(Porudzbina p: korpe){
                if(p != null){
                    p.setStatus(EnumStatus.otkazana);
                    p.setRestoran(null);
                    porudzbinaService.save(p);
                }
            }
        }

        session.invalidate();
         return ResponseEntity.ok("Korisnik uspesno izlogovan");
    }

    /*
    @GetMapping("/api/ucitaj-admine")
    public ResponseEntity<List<KorisnikDto>> ucitajAdmine(HttpSession session){
        List<Korisnik> listaAdmina = korisnikService.findAllByUlogaOrderById(EnumUloga.ADMIN);


        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik korisnik : listaAdmina){
            KorisnikDto dto = new KorisnikDto(korisnik);
            dtos.add(dto);
        }
        return ResponseEntity.ok(dtos);
    }
    */

    //Omoguceno menadzeru da pregleda odgovarajuce podatke
    @GetMapping("/api/menadzer-pregled") //TODO prebaciti u menadzerRestControler
    public ResponseEntity<MenadzerovPregledDto> prikaziPregledMenadzera(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije pronadjen",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.MENADZER){
                Menadzer ulogovanMenadzer = (Menadzer) session.getAttribute("korisnik");
                MenadzerovPregledDto pregledDto = new MenadzerovPregledDto();   //objekat dto koji metoda vraca

                pregledDto.setMenadzerovRestoran(ulogovanMenadzer.getRestoran());   //Setovanje odgovarajuceg restorana

                List<Porudzbina> porudzbineRestorana = porudzbinaService.findAllByRestoranOrderById(ulogovanMenadzer.getRestoran());
                pregledDto.setPorudzbineMenadzerovogRestorana(porudzbineRestorana);

                return ResponseEntity.ok(pregledDto);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije menadzer",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Omoguceno adminu da pregleda sve korisnike

    /*

    @GetMapping("/api/admin-pregled")
    public ResponseEntity<PregledAdminaDto> prikaziPregledAdmina(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.ADMIN){
                Admin ulogovaniAdmin = (Admin) session.getAttribute("korisnik");
                PregledAdminaDto pregledDto = new PregledAdminaDto();   //objekat dto koji metoda vraca

                List<Menadzer> menadzeriMedjuKorisnicima = menadzerService.findAll();
                pregledDto.setListaMenadzera(menadzeriMedjuKorisnicima);

                List<Dostavljac> dostavljaciMedjuKorisnicima = dostavljacService.findAll();
                pregledDto.setListaDostavljaca(dostavljaciMedjuKorisnicima);

                List<Kupac> kupciMedjuKorisnicima = kupacService.findAll();
                pregledDto.setListaKupaca(kupciMedjuKorisnicima);



                List<Admin> adminiMedjuKorisnicima = adminService.findAll();
                pregledDto.setListaAdmina(adminiMedjuKorisnicima);

                return ResponseEntity.ok(pregledDto);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije admin",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    */

    @GetMapping("/api/admin-pregled")
    public ResponseEntity<List<Korisnik>> prikaziPregledAdmina(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.ADMIN){

                List<Korisnik> sviKorisnici = korisnikService.findAll();
                return ResponseEntity.ok(sviKorisnici);
            }
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije admin",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }

    //Update podataka
    @PutMapping("/api/update-licni-podaci")
    public ResponseEntity<Korisnik> updateLicnihPodataka(@RequestBody UpdateKorisnikaDto azuriranKorisnik, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{

            if(azuriranKorisnik.getKorisnickoIme() != null && !azuriranKorisnik.getKorisnickoIme().isEmpty()) {
                ulogovaniKorisnik.setKorisnickoIme(azuriranKorisnik.getKorisnickoIme());
            }

            if(azuriranKorisnik.getIme() != null && !azuriranKorisnik.getIme().isEmpty()) {
                ulogovaniKorisnik.setIme(azuriranKorisnik.getIme());
            }

            if(azuriranKorisnik.getPrezime() != null && !azuriranKorisnik.getPrezime().isEmpty()) {
                ulogovaniKorisnik.setPrezime(azuriranKorisnik.getPrezime());
            }

            if(azuriranKorisnik.getLozinka() != null && !azuriranKorisnik.getLozinka().isEmpty()) {
                ulogovaniKorisnik.setLozinka(azuriranKorisnik.getLozinka());
            }

            ulogovaniKorisnik.setPol(azuriranKorisnik.getPol());
            ulogovaniKorisnik.setDatumRodjenja(azuriranKorisnik.getDatumRodjenja());


            final Korisnik updatedKorisnik = korisnikService.save(ulogovaniKorisnik);
            return ResponseEntity.ok(updatedKorisnik);
        }
    }

    //Pregled podaatka
    @GetMapping("/api/pregled-licni-podaci")
    public ResponseEntity<Korisnik> pregledLicnihPodataka(HttpSession session){


        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{

            return ResponseEntity.ok(ulogovaniKorisnik);
        }
    }


    @GetMapping("/api/logovani-korisnik")
    public ResponseEntity<Korisnik> getEmployees(HttpSession session){

        Korisnik logovaniKorisnik = (Korisnik) session.getAttribute("korisnik");
        if(logovaniKorisnik == null) {
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(logovaniKorisnik);
    }
    @PutMapping("/api/admin-pregled/pretraga")
    public ResponseEntity<List<PretragaKorisnikaDto>> pretraziKorisnike(@RequestBody PretragaKorisnikaDto dtoPretraga, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(ulogovaniKorisnik == null){
            return new ResponseEntity(
                    "Korisnik nije ulogovan",
                    HttpStatus.NOT_FOUND);
        }else{
            if(ulogovaniKorisnik.getUloga() == EnumUloga.ADMIN){

                List<Korisnik> sviKorisnici = korisnikService.findAll();

                List<PretragaKorisnikaDto> rezultatPretrage = new ArrayList<>();

                if(!dtoPretraga.getIme().isEmpty()) {

                    for (Korisnik korisnik : sviKorisnici) {

                        if (korisnik.getIme().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getIme().toLowerCase(Locale.ROOT)) != -1) {

                            PretragaKorisnikaDto k = new PretragaKorisnikaDto(korisnik);

                            boolean postoji = false;
                            // rezliciti kriterijumi pretrage mogu odgovarati istom korisniku
                            // ako je rezultat pretrage, tj. korisnik vec u listi ne dodaje se ponovo
                            if(!rezultatPretrage.isEmpty()){
                                for(PretragaKorisnikaDto dto : rezultatPretrage){
                                    if(dto.getKorisnickoIme().equals(k.getKorisnickoIme())){
                                        postoji = true;
                                        break;
                                    }
                                }
                            }

                            if(postoji == false){
                                rezultatPretrage.add(k);
                            }
                        }
                    }
                }

                if(!dtoPretraga.getKorisnickoIme().isEmpty()) {

                    for (Korisnik korisnik : sviKorisnici) {

                        if (korisnik.getKorisnickoIme().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getKorisnickoIme().toLowerCase(Locale.ROOT)) != -1) {

                            PretragaKorisnikaDto k = new PretragaKorisnikaDto(korisnik);

                            boolean postoji = false;

                            if(!rezultatPretrage.isEmpty()){
                                for(PretragaKorisnikaDto dto : rezultatPretrage){
                                    if(dto.getKorisnickoIme().equals(k.getKorisnickoIme())){
                                        postoji = true;
                                        break;
                                    }
                                }
                            }

                            if(postoji == false){
                                rezultatPretrage.add(k);
                            }
                        }
                    }
                }

                if(!dtoPretraga.getPrezime().isEmpty()) {

                    for (Korisnik korisnik : sviKorisnici) {

                        if (korisnik.getPrezime().toLowerCase(Locale.ROOT).indexOf(dtoPretraga.getPrezime().toLowerCase(Locale.ROOT)) != -1) {

                            PretragaKorisnikaDto k = new PretragaKorisnikaDto(korisnik);

                            boolean postoji = false;

                            if(!rezultatPretrage.isEmpty()){
                                for(PretragaKorisnikaDto dto : rezultatPretrage){
                                    if(dto.getKorisnickoIme().equals(k.getKorisnickoIme())){
                                        postoji = true;
                                        break;
                                    }
                                }
                            }

                            if(postoji == false){
                                rezultatPretrage.add(k);
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
            else{
                return new ResponseEntity(
                        "Ulogovani korisnik nije admin",
                        HttpStatus.UNAUTHORIZED);
            }
        }
    }
}
