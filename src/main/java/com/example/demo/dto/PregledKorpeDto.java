package com.example.demo.dto;

import com.example.demo.entity.Artikal;

import java.util.ArrayList;
import java.util.List;

public class PregledKorpeDto {

    private List<PregledArtiklaDto> pregledArtikala = new ArrayList<>();
    private double ukupnaCenaPorudzbine;

    public PregledKorpeDto(){}

    public PregledKorpeDto(List<PregledArtiklaDto> pregledArtikala, double ukupnaCenaPorudzbine) {
        this.pregledArtikala = pregledArtikala;
        this.ukupnaCenaPorudzbine = ukupnaCenaPorudzbine;
    }

    public void dodaj(PregledArtiklaDto artikal){
        this.pregledArtikala.add(artikal);
    }

    public List<PregledArtiklaDto> getPregledArtikala() {
        return pregledArtikala;
    }

    public void setPregledArtikala(List<PregledArtiklaDto> pregledArtikala) {
        this.pregledArtikala = pregledArtikala;
    }

    public double getUkupnaCenaPorudzbine() {
        return ukupnaCenaPorudzbine;
    }

    public void setUkupnaCenaPorudzbine(double ukupnaCenaPorudzbine) {
        this.ukupnaCenaPorudzbine = ukupnaCenaPorudzbine;
    }

    @Override
    public String toString() {
        return "PregledKorpeDto{" +
                "pregledArtikala=" + pregledArtikala +
                ", ukupnaCenaPorudzbine=" + ukupnaCenaPorudzbine +
                '}';
    }
}
