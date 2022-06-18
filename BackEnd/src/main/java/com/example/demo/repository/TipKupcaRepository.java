package com.example.demo.repository;

import com.example.demo.entity.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca, Long> {
    Optional<TipKupca> findByIme(String imeTipaKupca);
}
