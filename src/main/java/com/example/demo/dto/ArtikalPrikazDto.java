package com.example.demo.dto;

import com.example.demo.entity.Artikal;

public class ArtikalPrikazDto {

    private String naziv;

    private double cena;

    private String opis;

    public ArtikalPrikazDto() { }

    public  ArtikalPrikazDto(Artikal artikal){
        this.naziv = artikal.getNaziv();
        this.cena = artikal.getCena();
        this.opis = artikal.getOpis();
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    public String getOpis() {
        return opis;
    }
}
