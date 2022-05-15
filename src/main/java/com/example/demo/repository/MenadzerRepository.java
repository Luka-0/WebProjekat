package com.example.demo.repository;

import com.example.demo.entity.EnumUloga;
import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long>{
    List<Menadzer> findAll();
}
