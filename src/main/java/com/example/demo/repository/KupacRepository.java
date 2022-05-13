package com.example.demo.repository;

import com.example.demo.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long>{

}
