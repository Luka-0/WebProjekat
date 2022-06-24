package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Kupac
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kupca", referencedColumnName = "id")
    private Kupac kupac;

    //Restoran
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restorana", referencedColumnName = "id")
    private Restoran restoran;

    @Column
    private String tekstKomentara;

    @Column
    private int ocena;

    public Komentar(){}

    public Komentar(Kupac kupac, Restoran restoran, String tekstKomentara, int ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    //Getteri i setteri
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", restoran=" + restoran +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}
