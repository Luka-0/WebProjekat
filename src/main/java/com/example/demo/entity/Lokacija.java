package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double geografskaSirina;

    @Column
    private double geografskaDuzina;

    @Column
    private String adresa;


    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Lokacija{" +
                "id=" + id +
                ", geografskaSirina=" + geografskaSirina +
                ", geografskaDuzina=" + geografskaDuzina +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
