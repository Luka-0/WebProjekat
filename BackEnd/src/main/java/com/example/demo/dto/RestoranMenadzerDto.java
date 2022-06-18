package com.example.demo.dto;

public class RestoranMenadzerDto {

    private String korisnickoIme;

    private long idRestorana;

    public  RestoranMenadzerDto() { }

    public RestoranMenadzerDto(String korinickoIme, long idRestorana) {
        this.korisnickoIme = korinickoIme;
        this.idRestorana = idRestorana;
    }

    public String getKorisnickoIme() {  return korisnickoIme;  }

    public long getIdRestorana() {  return idRestorana;  }

    public void setKorisnickoIme(String korisnickoIme) {  this.korisnickoIme = korisnickoIme;  }

    public void setIdRestorana(long idRestorana) {  this.idRestorana = idRestorana; }
}
