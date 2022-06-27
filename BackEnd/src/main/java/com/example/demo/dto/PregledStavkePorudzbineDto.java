package com.example.demo.dto;

import com.example.demo.entity.EnumTip;

public class PregledStavkePorudzbineDto {
    private String nazivArtikla;
    private double cenaArtikla;
    private double kolicinaArtikla;
    private int porucenaKolicina;
    private EnumTip tip;

    public PregledStavkePorudzbineDto(){}

    public PregledStavkePorudzbineDto(String nazivArtikla, double cenaArtikla, double kolicinaArtikla, int porucenaKolicina, EnumTip tip) {
        this.nazivArtikla = nazivArtikla;
        this.cenaArtikla = cenaArtikla;
        this.kolicinaArtikla = kolicinaArtikla;
        this.porucenaKolicina = porucenaKolicina;
        this.tip = tip;
    }

    public EnumTip getTip() {
        return tip;
    }

    public void setTip(EnumTip tip) {
        this.tip = tip;
    }

    public int getPorucenaKolicina() {
        return porucenaKolicina;
    }

    public void setPorucenaKolicina(int porucenaKolicina) {
        this.porucenaKolicina = porucenaKolicina;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public double getCenaArtikla() {
        return cenaArtikla;
    }

    public void setCenaArtikla(double cenaArtikla) {
        this.cenaArtikla = cenaArtikla;
    }

    public double getKolicinaArtikla() {
        return kolicinaArtikla;
    }

    public void setKolicinaArtikla(double kolicinaArtikla) {
        this.kolicinaArtikla = kolicinaArtikla;
    }

    @Override
    public String toString() {
        return "PregledStavkePorudzbineDto{" +
                "nazivArtikla='" + nazivArtikla + '\'' +
                ", cenaArtikla=" + cenaArtikla +
                ", kolicinaArtikla=" + kolicinaArtikla +
                ", porucenaKolicina=" + porucenaKolicina +
                ", tip=" + tip +
                '}';
    }
}
