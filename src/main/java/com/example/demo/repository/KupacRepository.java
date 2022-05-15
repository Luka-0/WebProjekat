package com.example.demo.repository;

import com.example.demo.entity.Kupac;
import com.example.demo.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long>{
    List<Kupac> findAll();
}
