package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.EnumUloga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.TipKupca;
import com.example.demo.service.KupacService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.KorisnikService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KupacService kupacService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

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

        TipKupca tipKupca = new TipKupca();
        tipKupca.setIme("Novi kupac");
        tipKupca.setTrazeniBrojBodova(0);
        tipKupca.setPopust(0);

        noviKupac.setTk(tipKupca);
        this.kupacService.save(noviKupac);

        return "Korisnik" + newDto.getKorisnickoIme() + "je uspesno registrovan";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session){
        //provera podataka
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Uneti podaci su neispravni", HttpStatus.BAD_REQUEST);

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);

        session.setAttribute("korisnik", ulogovaniKorisnik);
        return ResponseEntity.ok("Korisnik " + ulogovaniKorisnik.getKorisnickoIme() + " je uspesno ulogovan!");
    }

    @PostMapping("api/logout")
    public ResponseEntity Logout(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Zabrana", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Korisnik uspesno izlogovan", HttpStatus.OK);
    }

    /*
    @GetMapping("/api/logovani-korisnici")
    public ResponseEntity<List<LoginDto>> getEmployees(HttpSession session){
        List<Korisnik> employeeList = korisnikService.findAll();

        Korisnik loggedEmployee = (Korisnik) session.getAttribute("korisnik");
        if(loggedEmployee == null) {
            System.out.println("Nema sesije");
        } else {
            System.out.println(loggedEmployee);
        }


        return ResponseEntity.ok(null);
    }
*/








}
