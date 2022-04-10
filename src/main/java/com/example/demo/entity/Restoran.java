package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naziv;

    @Column
    private String tipRestorana;

    //Restoran moze imate vise artikala
   // @OneToMany(mappedBy = "restoran",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Set<Artikal> artikli = new HashSet<>();


    //Restoran se nalazi na jednoj lokacij
    @OneToOne(mappedBy = "restoran",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Lokacija lokacija;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
