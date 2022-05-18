package com.example.demo.repository;

import com.example.demo.entity.Dostavljac;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Porudzbina;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
    List<Porudzbina> findAllByRestoranOrderByUuid(Restoran restoran);
    List<Porudzbina> findAllByKupac(Kupac kupac);
    List<Porudzbina> findAll();
}
