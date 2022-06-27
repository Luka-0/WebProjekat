package com.example.demo.repository;

import com.example.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
    List<Porudzbina> findAllByRestoranOrderByUuid(Restoran restoran);
    List<Porudzbina> findAllByKupac(Kupac kupac);
    List<Porudzbina> findAll();
    List<Porudzbina> findAllByStatus(EnumStatus status);
    List<Porudzbina> findAllByStatusAndKupac_Id(EnumStatus status, long id);
    Porudzbina findFirstByStatusAndKupac_Id(EnumStatus status, long id);
    Optional<Porudzbina> findByUuid(UUID uuid);
}
