package com.example.demo.dto;

public class RestoranMenadzerDto {

    private String korisnickoIme;


    public  RestoranMenadzerDto() { }

    public RestoranMenadzerDto(String korinickoIme) {
        this.korisnickoIme = korinickoIme;
    }

    public String getKorisnickoIme() {  return korisnickoIme;  }

    public void setKorisnickoIme(String korisnickoIme) {  this.korisnickoIme = korisnickoIme;  }

}
