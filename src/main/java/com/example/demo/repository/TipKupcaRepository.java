package com.example.demo.repository;

import com.example.demo.entity.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca, Long> {
}
