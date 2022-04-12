package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_restorana", referencedColumnName = "id")
    private Restoran restoran;

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Menadzer{" +
                "id=" + id +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", p='" + pol + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", uloga='" + uloga + '\'' +
                ", restoran=" + restoran +
                '}';
    }
}
