package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StavkaPorudzbine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Veza ka porudzbinama
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "uuid_porudzbine", referencedColumnName = "uuid")
    private Porudzbina porudzbine;

    //Veza ka artiklima
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_artikla", referencedColumnName = "id")
    private Artikal artkli;

    @Column
    private int broj_porucenog_artikla;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Porudzbina getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Porudzbina porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Artikal getArtkli() {
        return artkli;
    }

    public void setArtkli(Artikal artkli) {
        this.artkli = artkli;
    }

    public int getBroj_porucenih_artikala() {
        return broj_porucenog_artikla;
    }

    public void setBroj_porucenih_artikala(int broj_porucenih_artikala) {
        this.broj_porucenog_artikla = broj_porucenih_artikala;
    }

    @Override
    public String toString() {
        return "StavkaPorudzbine{" +
                "id=" + id +
                ", porudzbine=" + porudzbine +
                ", artkli=" + artkli +
                ", broj_porucenog_artikla=" + broj_porucenog_artikla +
                '}';
    }
}
