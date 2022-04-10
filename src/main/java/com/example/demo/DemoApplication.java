package com.example.demo;

import com.example.demo.entity.Korisnik;
import com.example.demo.entity.Kupac;
import com.example.demo.entity.Dostavljac;

import com.example.demo.repository.KorisnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	@Autowired
	private KorisnikRepository korisnikRep;

	@Override
	public void run(String... args) {

		//  kreiramo novi objekat klase Korisnik
		Korisnik k = new Korisnik();

		k.setIme("Jovanka");
		k.setPrezime("Jovkić");
		k.setUloga("Kupac");
		k.setP("Z");
		k.setKorisnickoIme("jj_742");
		k.setLozinka("******");


		Kupac kupac1 = new Kupac();
		kupac1.setIme("Marko");
		kupac1.setPrezime("Markovic");
		kupac1.setUloga("Kupac");
		kupac1.setP("M");
		kupac1.setKorisnickoIme("jj_742");
		kupac1.setLozinka("******");
		kupac1.setBrojSakupljenihBodova(5);

		// čuvamo objekate u bazi
		this.korisnikRep.save(k);
		this.korisnikRep.save(kupac1);

		List<Korisnik> korisnici = this.korisnikRep.findAll();

		for (Korisnik kk : korisnici){
			System.out.println(kk);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
