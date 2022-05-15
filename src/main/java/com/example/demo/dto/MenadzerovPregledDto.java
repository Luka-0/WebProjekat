package com.example.demo.dto;

import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;

import java.util.ArrayList;
import java.util.List;

public class MenadzerovPregledDto {
    private Restoran menadzerovRestoran;
    private List<Porudzbina> porudzbineMenadzerovogRestorana = new ArrayList<>();

    public MenadzerovPregledDto(){}

    public MenadzerovPregledDto(Restoran menadzerovRestoran, List<Porudzbina> porudzbineMenadzerovogRestorana) {
        this.menadzerovRestoran = menadzerovRestoran;
        this.porudzbineMenadzerovogRestorana = porudzbineMenadzerovogRestorana;
    }

    public Restoran getMenadzerovRestoran() {
        return menadzerovRestoran;
    }

    public void setMenadzerovRestoran(Restoran menadzerovRestoran) {
        this.menadzerovRestoran = menadzerovRestoran;
    }

    public List<Porudzbina> getPorudzbineMenadzerovogRestorana() {
        return porudzbineMenadzerovogRestorana;
    }

    public void setPorudzbineMenadzerovogRestorana(List<Porudzbina> porudzbineMenadzerovogRestorana) {
        this.porudzbineMenadzerovogRestorana = porudzbineMenadzerovogRestorana;
    }
}
