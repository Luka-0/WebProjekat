package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    private UUID uuid = UUID.randomUUID();

    //Artikli (Porudzbina sadrzi vise artikala)
    @ManyToMany
    @JoinTable(name = "poruceni_artikli",
            joinColumns = @JoinColumn(name = "uuid_porudzbine", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "id_artikla", referencedColumnName = "id"))
    private Set<Artikal> artikli = new HashSet<>();

    //Restoran (Porudzbina dolazi iz jednog restorana)
    //Mozda staviti da bude M:N ?
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_restorana", referencedColumnName = "id")
    private Restoran restoran;

    @Column
    private String datum_i_vreme;

    @Column
    private float cena;

    //Kupac
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;

    @Column
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

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina: " +
                " \n    uuid=" + uuid +
                ",\n    kupac = " + kupac +
                ",\n    artikli = " + artikli +
                ",\n    restoran = " + restoran +
                ",\n    datum_i_vreme = '" + datum_i_vreme + '\'' +
                ",\n    cena = " + cena +
                ",\n    status = '" + status + '\'';
    }
}
