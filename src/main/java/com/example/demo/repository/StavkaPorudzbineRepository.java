package com.example.demo.repository;

import com.example.demo.entity.Restoran;
import com.example.demo.entity.StavkaPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StavkaPorudzbineRepository extends JpaRepository<StavkaPorudzbine, Long> {
}
