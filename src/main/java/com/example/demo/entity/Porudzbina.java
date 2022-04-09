package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Kupac kupac;


    // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Dostavljac dostavljac;
}
