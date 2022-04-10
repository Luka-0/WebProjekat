package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column
    protected String korisnickoIme;

    @Column
    protected String lozinka;

    @Column
    protected String ime;

    @Column
    protected String prezime;

    @Column
    protected String p;

    @Column
    protected Date datumRodjenja;

    @Column
    protected String uloga;

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

    public String getP() {
        return p;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getUloga() {
        return uloga;
    }

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

    public void setP(String p) {
        this.p = p;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
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
