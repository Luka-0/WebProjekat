package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StavkaPorudzbine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Veza ka artiklima
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_artikla", referencedColumnName = "id")
    private Artikal artikal;

    @Column
    private int porucenaKolicina;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getPorucenaKolicina() {
        return porucenaKolicina;
    }

    public void setPorucenaKolicina(int porucenaKolicina) {
        this.porucenaKolicina = porucenaKolicina;
    }

    @Override
    public String toString() {
        return "StavkaPorudzbine{" +
                "id=" + id +
                ", artikal=" + artikal +
                ", porucenaKolicina=" + porucenaKolicina +
                '}';
    }
}
