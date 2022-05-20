package com.example.demo.repository;

import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Spliterator;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {

    Optional<Restoran> findByNaziv(String naziv);
    Optional<Restoran> findByLokacija(Lokacija lokacija);
    Optional<Restoran> findByTipRestorana(String tipRestorana);
    Optional<Restoran> findById(long idRestorana);

}
