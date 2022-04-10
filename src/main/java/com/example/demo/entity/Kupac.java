package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "kupac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Porudzbina> svePorudzbine;

    @Column
    private int brojSakupljenihBodova;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipKupca", referencedColumnName = "id")
    private TipKupca tk;

    public Kupac() {
        this.svePorudzbine =  new HashSet<>();
        this.brojSakupljenihBodova = 0;
        //this.tk = null;
    }

    public Set<Porudzbina> getSvePorudzbine() {
        return svePorudzbine;
    }

    public int getBrojSakupljenihBodova() {
        return brojSakupljenihBodova;
    }

    public TipKupca getTk() {
        return tk;
    }

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {
        this.svePorudzbine = svePorudzbine;
    }

    public void setBrojSakupljenihBodova(int brojSakupljenihBodova) {
        this.brojSakupljenihBodova = brojSakupljenihBodova;
    }

    public void setTk(TipKupca tk) {
        this.tk = tk;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", p='" + p + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga='" + uloga + '\'' +
                ", svePorudzbine=" + svePorudzbine +
                ", brojSakupljenihBodova=" + brojSakupljenihBodova +
                ", tk=" + tk +
                '}';
    }
}
