package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class PregledKorpeDto {

    private List<PregledStavkePorudzbineDto> pregledArtikala = new ArrayList<>();
    private double ukupnaCenaPorudzbine;

    public PregledKorpeDto(){}

    public PregledKorpeDto(List<PregledStavkePorudzbineDto> pregledArtikala, double ukupnaCenaPorudzbine) {
        this.pregledArtikala = pregledArtikala;
        this.ukupnaCenaPorudzbine = ukupnaCenaPorudzbine;
    }

    public void dodaj(PregledStavkePorudzbineDto artikal){
        this.pregledArtikala.add(artikal);
    }

    public List<PregledStavkePorudzbineDto> getPregledArtikala() {
        return pregledArtikala;
    }

    public void setPregledArtikala(List<PregledStavkePorudzbineDto> pregledArtikala) {
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
