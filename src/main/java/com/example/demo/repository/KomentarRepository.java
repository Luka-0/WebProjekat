package com.example.demo.repository;

import com.example.demo.entity.Komentar;
import com.example.demo.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {

    public List<Komentar> findAllByRestoran(Restoran restoran);

}
