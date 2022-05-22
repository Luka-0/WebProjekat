package com.example.demo.dto;

import com.example.demo.entity.EnumTip;

public class NewArtikalDto {

    private String naziv;
    private double cena;
    private String tip;
    private double kolicina;
    private String opis;

    //dodati polje za sliku

    public NewArtikalDto() { }

    public NewArtikalDto(String naziv, double cena, String tip, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    public String getTip() {
        return tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
