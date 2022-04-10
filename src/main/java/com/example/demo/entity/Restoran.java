package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Restoran implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}
