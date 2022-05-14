package com.example.demo.dto;

import com.example.demo.entity.EnumPol;
import com.example.demo.entity.EnumUloga;

public class KorisnikDto {
    private String ime;
    private String prezime;
    private EnumPol pol;
    private EnumUloga uloga;

    public  KorisnikDto(){}

    public KorisnikDto(String ime, String prezime, EnumPol pol, EnumUloga uloga) {
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.uloga = uloga;
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

    public EnumUloga getUloga() {
        return uloga;
    }

    public void setUloga(EnumUloga uloga) {
        this.uloga = uloga;
    }
}
