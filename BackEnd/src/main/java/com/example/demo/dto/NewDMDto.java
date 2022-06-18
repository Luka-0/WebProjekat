package com.example.demo.dto;

import com.example.demo.entity.EnumPol;
import com.example.demo.entity.EnumUloga;

public class NewDMDto {

    private String ime;

    private String prezime;

    private String korisnickoIme;

    private String lozinka;

    private EnumUloga uloga;

    private EnumPol pol;

    public NewDMDto() { }

    public NewDMDto(String ime, String prezime,
                    String korisnickoIme, String lozinka, EnumUloga uloga,
                    EnumPol pol) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
        this.pol = pol;
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

    public String getLozinka() {
        return lozinka;
    }

    public EnumUloga getUloga() {
        return uloga;
    }

    public EnumPol getPol() {
        return pol;
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

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setUloga(EnumUloga uloga) {
        this.uloga = uloga;
    }

    public void setPol(EnumPol pol) {
        this.pol = pol;
    }
}
