package com.example.demo;

import com.example.demo.entity.*;

import com.example.demo.repository.KorisnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	EnumPol p1, p2;
	EnumUloga U1, U2;
	@Autowired
	private KorisnikRepository korisnikRep;

	@Override
	public void run(String... args) {


		//  kreiramo novi objekat klase Korisnik
		Korisnik k = new Korisnik();

		k.setIme("Jovanka");
		k.setPrezime("Jovkić");
		k.setUloga(U1.KUPAC);
		k.setPol(p1.Z);
		k.setKorisnickoIme("jj_742");
		k.setLozinka("******");


		Kupac kupac1 = new Kupac();
		kupac1.setIme("Marko");
		kupac1.setPrezime("Markovic");
		kupac1.setUloga(U2.KUPAC);
		kupac1.setPol(p2.M);
		kupac1.setKorisnickoIme("mm_742");
		kupac1.setLozinka("******");
		kupac1.setBrojSakupljenihBodova(5);

		// čuvamo objekate u bazi
		this.korisnikRep.save(k);
		this.korisnikRep.save(kupac1);

		List<Korisnik> korisnici = this.korisnikRep.findAll();

		for (Korisnik kk : korisnici){
			System.out.println(kk);
		}

		System.out.println("\n\n\n\n");

		//Novi artikal
		Artikal prviArtikal = new Artikal();
		prviArtikal.setCena(100);

		prviArtikal.setKolicina(EnumKolicina.GRAMI);
		prviArtikal.setTip(EnumTip.JELO);
		prviArtikal.setNaziv("Cokolada");
		prviArtikal.setOpis("Mlecna");

		System.out.println(prviArtikal.toString());

		//Novi restoran
		Restoran prviRestoran = new Restoran();
		prviRestoran.setNaziv("Moj lepi restoran");
		prviRestoran.setTipRestorana("SrBski");

			//Nova lokacija
			Lokacija prvaLokacija = new Lokacija();
			prvaLokacija.setAdresa("Jug Bogdana bb");
			prvaLokacija.setGeografskaDuzina(22);
			prvaLokacija.setGeografskaSirina(33);

		prviRestoran.setLokacija(prvaLokacija);

		prviRestoran.dodajArtikal(prviArtikal);

		System.out.println("Moj restoran: \n" + prviRestoran.toString());

		//Testiraj komentar
		Komentar prviCom = new Komentar();
		prviCom.setOcena(5);
		prviCom.setRestoran(prviRestoran);
		prviCom.setTekstKomentara("Ovo je tekst prvog komentara");
			//Kupac
			Kupac mojKupac = new Kupac();

		prviCom.setKupac(mojKupac);
		System.out.println("Moj com : \n" + prviCom.toString());


		//Testiraj porudzbinu
		Porudzbina prvaPorudzbina = new Porudzbina();
		prvaPorudzbina.setRestoran(prviRestoran);
		prvaPorudzbina.setKupac(mojKupac);
		prvaPorudzbina.setStatus(EnumStatus.ceka_dostavljaca);
		prvaPorudzbina.setCena(2000);
		prvaPorudzbina.setDatum_i_vreme(LocalDateTime.now());

		System.out.println("Porudzbina : \n" + prvaPorudzbina.toString());
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
