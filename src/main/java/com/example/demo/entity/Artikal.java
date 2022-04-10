package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Artikal se nalazi u jednom restoranu
    //Ne moze se fizicki nalaziti na 2 mesta istovremeno
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idRestorana", referencedColumnName = "id")
    private Restoran restoran;


    //Vise artikala moze pripadati jednoj porudzbini
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuidPorudzbine", referencedColumnName = "uuid")
    private Porudzbina porudzbina;

    //Getteri i Setteri
    @Column
    private String naziv;

    @Column
    private float cena;

    @Column
    private String tip;

    @Column
    private float kolicina;

    @Column
    private String opis;

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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public float getKolicina() {
        return kolicina;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", restoran=" + restoran +
                ", porudzbina=" + porudzbina +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tip='" + tip + '\'' +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                '}';
    }
}
