package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uuid;

    //Kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    //Artikli (Porudzbina sadrzi vise artikala)
    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Artikal> artikli = new HashSet<>();

    //Restoran (Porudzbina dolazi iz jednog restorana)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idRestorana", referencedColumnName = "id")
    private Restoran restoran;

    private String datum_i_vreme;   //promeniti na DateTime??
    private float cena;

    private String status;

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getDatum_i_vreme() {
        return datum_i_vreme;
    }

    public void setDatum_i_vreme(String datum_i_vreme) {
        this.datum_i_vreme = datum_i_vreme;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "uuid=" + uuid +
                ", kupac=" + kupac +
                ", artikli=" + artikli +
                ", restoran=" + restoran +
                ", datum_i_vreme='" + datum_i_vreme + '\'' +
                ", cena=" + cena +
                ", status='" + status + '\'' +
                '}';
    }
}
