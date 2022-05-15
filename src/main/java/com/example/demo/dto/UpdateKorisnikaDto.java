package com.example.demo.dto;

import com.example.demo.entity.EnumPol;

import java.util.Date;

public class UpdateKorisnikaDto {
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private EnumPol pol;
    private Date datumRodjenja = new Date();

    public UpdateKorisnikaDto(){}

    public UpdateKorisnikaDto(String korisnickoIme, String lozinka, String ime, String prezime, EnumPol pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public EnumPol getPol() {
        return pol;
    }

    public void setPol(EnumPol pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
