package com.example.demo.dto;



public class PrikazListeArtikalaDto {

    private String naziv;
    private double cena;
    private String opis;
    private double kolicina;

    public PrikazListeArtikalaDto() { }

    public PrikazListeArtikalaDto(String naziv, double cena, String opis, double kolicina) {
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
        this.kolicina = kolicina;
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

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "PrikazListeArtikalaDto{" +
                "naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", opis='" + opis + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }
}