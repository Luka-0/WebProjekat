package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Artikal implements Serializable {
   //Polja
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String naziv;

    @Column
    private float cena;

    @Enumerated(EnumType.STRING)
    private EnumTip tip;

    @Enumerated(EnumType.STRING)
    private EnumKolicina kolicina;

    @Column
    private String opis;

    //Getteri i Setteri
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

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public EnumTip getTip() {
        return tip;
    }

    public void setTip(EnumTip tip) {
        this.tip = tip;
    }

    public EnumKolicina getKolicina() {
        return kolicina;
    }

    public void setKolicina(EnumKolicina kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tip='" + tip + '\'' +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                '}';
    }
}
