package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(unique = true, nullable = false)
    protected String korisnickoIme;

    @Column(nullable = false)
    protected String lozinka;

    @Column
    protected String ime;

    @Column
    protected String prezime;

    @Enumerated(EnumType.STRING)
    protected EnumPol pol;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Date datumRodjenja = new Date();

    @Enumerated(EnumType.STRING)
    protected EnumUloga uloga;

    public Korisnik(){}

    public Korisnik(long id, String korisnickoIme, String lozinka, String ime, String prezime, EnumPol pol, Date datumRodjenja, EnumUloga uloga) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
    }

    public long getId() {
        return id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public EnumPol getPol() {
        return pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public EnumUloga getUloga() {   return uloga;  }

    public void setId(long id) {
        this.id = id;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setPol(EnumPol pol) {   this.pol = pol; }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setUloga(EnumUloga uloga) { this.uloga = uloga; }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol=" + pol +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga=" + uloga +
                '}';
    }
}
