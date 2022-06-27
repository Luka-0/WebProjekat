package com.example.demo.dto;

import com.example.demo.entity.EnumTip;
import com.example.demo.entity.Restoran;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

public class PregledKorpeDto {

    private List<PregledStavkePorudzbineDto> pregledArtikala = new ArrayList<>();
    private double ukupnaCenaPorudzbine;
    private Restoran restoran;
    private EnumTip tip;
    public PregledKorpeDto(){}

    public PregledKorpeDto(List<PregledStavkePorudzbineDto> pregledArtikala, double ukupnaCenaPorudzbine, Restoran restoran, EnumTip tip) {
        this.pregledArtikala = pregledArtikala;
        this.ukupnaCenaPorudzbine = ukupnaCenaPorudzbine;
        this.restoran = restoran;
        this.tip = tip;
    }

    public EnumTip getTip() {
        return tip;
    }

    public void setTip(EnumTip tip) {
        this.tip = tip;
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "PregledKorpeDto{" +
                "pregledArtikala=" + pregledArtikala +
                ", ukupnaCenaPorudzbine=" + ukupnaCenaPorudzbine +
                ", restoran=" + restoran +
                ", tip=" + tip +
                '}';
    }
}
