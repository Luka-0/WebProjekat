package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija, Long>{

    List<Lokacija> findAllByAdresaOrderById(String adresa);

    List<Lokacija> findAllByGeografskaDuzina(float grografska_sirina);


}
