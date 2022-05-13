package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    private UUID uuid = UUID.randomUUID();

    //Stavke porudzbine
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid_porudzbine", referencedColumnName = "uuid")
    private Set<StavkaPorudzbine> stavkePorudzbine = new HashSet<>();

    //Restoran
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restorana", referencedColumnName = "id")
    private Restoran restoran;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVreme = new Date();

    @Column
    private double cena;

    //Kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public Set<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(Set<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Date getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(Date datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "uuid=" + uuid +
                ", stavkePorudzbine=" + stavkePorudzbine +
                ", restoran=" + restoran +
                ", datumIVreme=" + datumIVreme +
                ", cena=" + cena +
                ", kupac=" + kupac +
                ", status=" + status +
                '}';
    }
}
