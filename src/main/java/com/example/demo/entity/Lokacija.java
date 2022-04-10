package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private float geografskaSirina;

    @Column
    private float geografskaDuzina;

    @Column
    private String adresa;

    //Na odredjenoj lokaciji se nalazi jedan restoran
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Restoran restoran;

    public float getGeografskaSirina() {
        return geografskaSirina;
    }

    public float getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setGeografskaSirina(float geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public void setGeografskaDuzina(float geografskaDuzina) {
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

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
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
