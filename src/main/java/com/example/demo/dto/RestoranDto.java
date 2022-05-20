package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class RestoranDto {

    private String nazivRestorana;

    private String tipRestorana;

    private Lokacija lokacijaRestorana;

    public RestoranDto() { }
    public RestoranDto(String nazivRestorana, String tipRestorana, Lokacija lokacijaRestorana) {
        this.nazivRestorana = nazivRestorana;
        this.tipRestorana = tipRestorana;
        this.lokacijaRestorana = lokacijaRestorana;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public Lokacija getLokacijaRestorana() {
        return lokacijaRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacijaRestorana(Lokacija lokacijaRestorana) {
        this.lokacijaRestorana = lokacijaRestorana;
    }
}
