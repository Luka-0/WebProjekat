package com.example.demo.repository;

import com.example.demo.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
    Optional<Artikal> findById(long id);
}
