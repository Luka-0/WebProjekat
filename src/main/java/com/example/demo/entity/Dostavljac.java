package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "Dostava")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public void dodajPorudzbinu(Porudzbina p){
        porudzbine.add(p);
    }

    public Dostavljac(){}

    @Override
    public String toString() {
        return "Dostavljac{" +
                "porudzbine=" + porudzbine +
                ", id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", p='" + pol + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga='" + uloga + '\'' +
                '}';
    }
}
