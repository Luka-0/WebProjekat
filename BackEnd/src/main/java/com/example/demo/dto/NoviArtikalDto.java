package com.example.demo.dto;

import com.example.demo.entity.EnumTip;

public class NoviArtikalDto {
    private String naziv;
    private double cena;
    private EnumTip tip;
    private double kolicina;
    private String opis;
    private String url;

    NoviArtikalDto(){}

    public NoviArtikalDto(String naziv, double cena, EnumTip tip, double kolicina, String opis, String url) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.url = url;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public EnumTip getTip() {
        return tip;
    }

    public void setTip(EnumTip tip) {
        this.tip = tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
