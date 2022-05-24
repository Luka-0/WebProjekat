package com.example.demo.dto;

import com.example.demo.entity.Lokacija;

public class RestoranDto {

    private String nazivRestorana;

    private String tipRestorana;

    private long idLokacije;

    public RestoranDto() { }
    public RestoranDto(String nazivRestorana, String tipRestorana, long idLokacije) {
        this.nazivRestorana = nazivRestorana;
        this.tipRestorana = tipRestorana;
        this.idLokacije = idLokacije;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public long getIdLokacije() {
        return idLokacije;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacijaRestorana(long lokacijaRestorana) {
        this.idLokacije = lokacijaRestorana;
    }
}
