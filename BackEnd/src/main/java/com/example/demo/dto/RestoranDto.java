package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class RestoranDto {

    private String nazivRestorana;

    private String tipRestorana;

    private Lokacija lokacija;

    public RestoranDto() { }
    public RestoranDto(String nazivRestorana, String tipRestorana, Lokacija lokacija) {
        this.nazivRestorana = nazivRestorana;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacijaRestorana(Lokacija lokacijaRestorana) {
        this.lokacija = lokacijaRestorana;
    }
}
