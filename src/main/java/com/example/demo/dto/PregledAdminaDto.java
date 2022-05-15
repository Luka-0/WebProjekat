package com.example.demo.dto;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Menadzer;

import java.util.ArrayList;
import java.util.List;

public class PregledAdminaDto {
    private List<Menadzer> listaMenadzera = new ArrayList<>();
    private List<Dostavljac> listaDostavljaca = new ArrayList<>();
    private List<Kupac> listaKupaca = new ArrayList<>();
    private List<Admin> listaAdmina = new ArrayList<>();

    public PregledAdminaDto(){}

    public PregledAdminaDto(List<Menadzer> listaMenadzera, List<Dostavljac> listaDostavljaca, List<Kupac> listaKupaca, List<Admin> listaAdmina) {
        this.listaMenadzera = listaMenadzera;
        this.listaDostavljaca = listaDostavljaca;
        this.listaKupaca = listaKupaca;
        this.listaAdmina = listaAdmina;
    }

    public List<Menadzer> getListaMenadzera() {
        return listaMenadzera;
    }

    public void setListaMenadzera(List<Menadzer> listaMenadzera) {
        this.listaMenadzera = listaMenadzera;
    }

    public List<Dostavljac> getListaDostavljaca() {
        return listaDostavljaca;
    }

    public void setListaDostavljaca(List<Dostavljac> listaDostavljaca) {
        this.listaDostavljaca = listaDostavljaca;
    }

    public List<Kupac> getListaKupaca() {
        return listaKupaca;
    }

    public void setListaKupaca(List<Kupac> listaKupaca) {
        this.listaKupaca = listaKupaca;
    }

    public List<Admin> getListaAdmina() {
        return listaAdmina;
    }

    public void setListaAdmina(List<Admin> listaAdmina) {
        this.listaAdmina = listaAdmina;
    }
}
