package com.example.demo.dto;

public class PregledStavkePorudzbineDto {
    private String nazivArtikla;
    private double cenaArtikla;
    private double kolicinaArtikla;
    private int porucenaKolicina;

    public PregledStavkePorudzbineDto(){}

    public PregledStavkePorudzbineDto(String nazivArtikla, double cenaArtikla, double kolicinaArtikla, int porucenaKolicina) {
        this.nazivArtikla = nazivArtikla;
        this.cenaArtikla = cenaArtikla;
        this.kolicinaArtikla = kolicinaArtikla;
        this.porucenaKolicina = porucenaKolicina;
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
        return "PregledArtiklaDto{" +
                "nazivArtikla='" + nazivArtikla + '\'' +
                ", cenaArtikla=" + cenaArtikla +
                ", kolicinaArtikla=" + kolicinaArtikla +
                ", porucenaKolicina=" + porucenaKolicina +
                '}';
    }
}
