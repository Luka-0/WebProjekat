package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long>{

    List<Lokacija> findAllByAdresaOrderById(String adresa);

    List<Lokacija> findAllByGeografskaDuzina(float grografska_sirina);


}
