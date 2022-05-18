package com.example.demo.dto;

import com.example.demo.entity.Artikal;
import com.example.demo.entity.EnumStatusRestorana;
import com.example.demo.entity.Komentar;
import com.example.demo.entity.Lokacija;

import java.util.List;
import java.util.Set;

public class PrikazRestoranaDto {

    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    private String status;

    private double prosecnaOcena;

    private List<KomentarRestoranaDto> komentari;

    private Set<ArtikalPrikazDto> artikli;

    public  PrikazRestoranaDto() { }

    public PrikazRestoranaDto(String naziv, String tipRestorana, Lokacija lokacija,
                              String status, double prosecnaOcena,
                              List<KomentarRestoranaDto> komentari, Set<ArtikalPrikazDto> artikli) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
        this.status = status;
        this.prosecnaOcena = prosecnaOcena;
        this.komentari = komentari;
        this.artikli = artikli;
    }

    //getters
    public String getNaziv() {
        return naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public String getStatus() {
        return status;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public List<KomentarRestoranaDto> getKomentari() {
        return komentari;
    }

    public Set<ArtikalPrikazDto> getArtikli() {
        return artikli;
    }

    //setters

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public void setKomentari(List<KomentarRestoranaDto> komentari) {
        this.komentari = komentari;
    }

    public void setArtikli(Set<ArtikalPrikazDto> artikli) {

         this.artikli = artikli;
    }
}
