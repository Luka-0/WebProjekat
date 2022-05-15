package com.example.demo.dto;


import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Restoran;


public class RestoranDto {

    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    public RestoranDto(){}
    public RestoranDto(String naziv, Lokacija lokacija, String tipRestorana){
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.tipRestorana = tipRestorana;
    }
    public RestoranDto(Restoran restoran){
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.lokacija = restoran.getLokacija();
    }

    public String getNaziv() {  return  naziv;  }
    public void setNaziv(String naziv) {  this.naziv = naziv;   }

    public String getTipRestorana() {  return  tipRestorana;  }
    public void setTipRestorana(String tip){  this.tipRestorana = tip;  }

    public Lokacija getLokacija() {  return  lokacija;  }
    public void setLokacija(Lokacija lokacija) {  this.lokacija = lokacija;  }

}
