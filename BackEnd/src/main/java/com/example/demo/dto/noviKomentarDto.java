package com.example.demo.dto;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Restoran;

public class noviKomentarDto {

    private String tekstKomentara;

    private int ocena;

    public noviKomentarDto(String tekstKomentara, int ocena) {
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }


    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }


    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
