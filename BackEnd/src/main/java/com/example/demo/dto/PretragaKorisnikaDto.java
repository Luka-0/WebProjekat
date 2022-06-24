package com.example.demo.dto;

import com.example.demo.entity.Korisnik;

public class PretragaKorisnikaDto {

    private String ime;

    private String prezime;

    private String korisnickoIme;

    public PretragaKorisnikaDto(String ime, String prezime, String korisnickoIme) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
    }

    public PretragaKorisnikaDto(Korisnik korisnik) {
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.korisnickoIme = korisnik.getKorisnickoIme();
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
}
