package com.example.demo.repository;

import com.example.demo.entity.Restoran;
import com.example.demo.entity.StavkaPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Long> {
    StavkaPorudzbine getById(long id);
}
