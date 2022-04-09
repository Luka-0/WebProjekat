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

/*
   @SpringBootApplication anotacija nastala je od @EnableAutoConfiguration anotacije koja
   upravlja konfiguracijom aplikacije.
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	/* Da bismo testirali repozitorijum, direktno smo pozvali u glavnoj klasi metode,
	   inace bi pozivi bili u npr. nekom od servisa.
	 */
	@Autowired
	private KorisnikRepository korisnikRep;

	@Override
	public void run(String... args) {

		//  kreiramo novi objekat klase Korisnik
		Korisnik k = new Korisnik();
		Korisnik k1 = new Korisnik();

		k.setIme("Jovanka");
		k.setPrezime("Jovkić");
		k.setUloga("Kupac");

		k1.setKorisnickoIme("username4");
		k1.setIme("name4");
		k1.setPrezime("lastName4");
		k1.setUloga("menadzer");

		Kupac kupac1 = new Kupac();
		kupac1.setIme("Marko");

		Kupac kupac2 = new Kupac();
		kupac2.setIme("Marko");

		// čuvamo objekate u bazi
		this.korisnikRep.save(k);
		this.korisnikRep.save(k1);
		this.korisnikRep.save(kupac1);
		this.korisnikRep.save(kupac2);


		List<Korisnik> korisnici = this.korisnikRep.findAll();

		for (Korisnik kk : korisnici){
			System.out.println(kk);
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
