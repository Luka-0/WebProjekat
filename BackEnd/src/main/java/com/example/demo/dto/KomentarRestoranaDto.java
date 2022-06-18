package com.example.demo.dto;

import com.example.demo.entity.Komentar;

public class KomentarRestoranaDto {

    private String korisnickoIme;

    private String textKomentara;

    private int ocena;

    public KomentarRestoranaDto() { }

    public KomentarRestoranaDto(Komentar komentar){
        this.korisnickoIme = komentar.getKupac().getKorisnickoIme();
        this.textKomentara = komentar.getTekstKomentara();
        this.ocena = komentar.getOcena();
    }

    //getters
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getTextKomentara() {
        return textKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    //setters

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setTextKomentara(String textKomentara) {
        this.textKomentara = textKomentara;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

}
