package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String ime;

    @Column
    private double popust;

    @Column
    private int trazeniBrojBodova;

    public TipKupca(){}

    public String getIme() {
        return ime;
    }

    public double getPopust() {
        return popust;
    }

    public int getTrazeniBrojBodova() {
        return trazeniBrojBodova;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public void setTrazeniBrojBodova(int trazeniBrojBodova) {
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TipKupca{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", popust=" + popust +
                ", trazeniBrojBodova=" + trazeniBrojBodova +
                '}';
    }
}
