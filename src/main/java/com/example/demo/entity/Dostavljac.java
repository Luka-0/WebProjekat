package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "porudzbina", referencedColumnName = "id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    @Override
    public String toString() {
        return "Dostavljac{" +
                "porudzbine=" + porudzbine +
                ", id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", p='" + p + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga='" + uloga + '\'' +
                '}';
    }
}
