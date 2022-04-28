package com.example.demo;

import com.example.demo.entity.*;

import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.*;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	EnumPol p1, p2;
	EnumUloga U1, U2;
	@Autowired
	private KorisnikRepository korisnikRep;

	@Autowired
	private LokacijaRepository lokacijaRep;

	@Autowired
	private PorudzbinaRepository porudzbinaRep;

	@Autowired
	private ArtikalRepository artikalRep;

	@Autowired
	private KupacRepository kupacRep;

	@Autowired
	private KomentarRepository KomentarRep;

	@Autowired
	private RestoranRepository restoranRep;

	@Autowired
	private MenadzerRepository menadzerRep;

	@Autowired
	private DostavljacRepository dostavljacRep;

	@Autowired
	private StavkaPorudzbineRepository stavkaPorudzbineRep;

	@Override
	public void run(String... args) {


	/*

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
		//this.korisnikRep.save(k);
		//this.korisnikRep.save(kupac1);

		//List<Korisnik> korisnici = this.korisnikRep.findAll();

		//for (Korisnik kk : korisnici){
		//	System.out.println(kk);
		//}

		System.out.println("\n\n\n\n");

		//Novi artikal
		Artikal prviArtikal = new Artikal();
		prviArtikal.setCena(100);

		prviArtikal.setKolicina(100);
		prviArtikal.setTip(EnumTip.JELO);
		prviArtikal.setNaziv("Cokolada");
		prviArtikal.setOpis("Mlecna");

		//System.out.println(prviArtikal.toString());

		Artikal drugiArtikal = new Artikal();
		drugiArtikal.setCena(200);
		drugiArtikal.setKolicina(500);
		drugiArtikal.setTip(EnumTip.PICE);
		drugiArtikal.setNaziv("Sok");
		drugiArtikal.setOpis("Negazirani");

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

		//System.out.println("Moj restoran: \n" + prviRestoran.toString());

		//-------------------------------------------------------------------
		Restoran drugiRestoran = new Restoran();
		drugiRestoran.setTipRestorana("Domaca hrana");
		drugiRestoran.setNaziv("Koliba");
			//Nova lokacija
			Lokacija drugaLokacija = new Lokacija();
			drugaLokacija.setAdresa("Dositeja Obradovica 21");
			drugaLokacija.setGeografskaDuzina(222);
			drugaLokacija.setGeografskaSirina(333);

		drugiRestoran.setLokacija(drugaLokacija);
		drugiRestoran.dodajArtikal(drugiArtikal);

		//Testiraj komentar
		Komentar prviCom = new Komentar();
		prviCom.setOcena(5);
		prviCom.setRestoran(prviRestoran);
		prviCom.setTekstKomentara("Ovo je tekst prvog komentara");
			//Kupac
			Kupac mojKupac = new Kupac();
			mojKupac.setIme("Marinko");
			mojKupac.setLozinka("lozinka123");
			mojKupac.setKorisnickoIme("Marinko_1");
			mojKupac.setPrezime("Rokvic");
			mojKupac.setPol(EnumPol.M);
			mojKupac.setUloga(EnumUloga.KUPAC);
			mojKupac.setBrojSakupljenihBodova(10);
				//Tip kupca dodat
				TipKupca tip1 = new TipKupca();
				tip1.setIme("Zlatni");
				tip1.setPopust(10);
				tip1.setTrazeniBrojBodova(10);
			mojKupac.setTk(tip1);

			Date datum = new Date();
			mojKupac.setDatumRodjenja(datum);
		prviCom.setKupac(mojKupac);

		//System.out.println("Moj com : \n" + prviCom.toString());


		//Testiraj porudzbinu
		Porudzbina prvaPorudzbina = new Porudzbina();
		prvaPorudzbina.setRestoran(prviRestoran);
		prvaPorudzbina.setKupac(mojKupac);
		prvaPorudzbina.setStatus(EnumStatus.ceka_dostavljaca);
		prvaPorudzbina.setCena(2000);
		prvaPorudzbina.dodajArtikal(prviArtikal);
		prvaPorudzbina.setDatum_i_vreme(datum);

		//System.out.println("Porudzbina : \n" + prvaPorudzbina.toString());

		//Druga porudzbina
		Porudzbina drugaPorudzbina = new Porudzbina();
		drugaPorudzbina.setRestoran(drugiRestoran);
		drugaPorudzbina.setKupac(kupac1);
		drugaPorudzbina.setStatus(EnumStatus.dostavljena);
		drugaPorudzbina.setCena(1040);
		drugaPorudzbina.setDatum_i_vreme(datum);
		drugaPorudzbina.dodajArtikal(drugiArtikal);

		//Treca porudzbina
		Porudzbina trecaPorudzbina = new Porudzbina();
		trecaPorudzbina.setRestoran(prviRestoran);
		trecaPorudzbina.setKupac(kupac1);
		trecaPorudzbina.setStatus(EnumStatus.otkazana);
		trecaPorudzbina.setCena(3241);
		trecaPorudzbina.setDatum_i_vreme(datum);
		trecaPorudzbina.dodajArtikal(prviArtikal);
		trecaPorudzbina.dodajArtikal(drugiArtikal);

		//Cuvanje artikala
		//this.artikalRep.save(prviArtikal);
		//this.artikalRep.save(drugiArtikal);

		//Cuvanje lokacija
		//this.lokacijaRep.save(prvaLokacija);
		//this.lokacijaRep.save(drugaLokacija);

		//Cuvanje komentara
		//this.KomentarRep.save(prviCom);

		//Cuvanje restorana
		//this.restoranRep.save(drugiRestoran);

		//Cuvanje porudzbina
		//this.porudzbinaRep.save(prvaPorudzbina);
		//this.porudzbinaRep.save(drugaPorudzbina);
		//this.porudzbinaRep.save(trecaPorudzbina);

		Menadzer prviMenazder = new Menadzer();
		prviMenazder.setRestoran(prviRestoran);
		prviMenazder.setIme("Radovan");
		prviMenazder.setLozinka("kuca321");
		prviMenazder.setPrezime("Radovanovic");
		prviMenazder.setPol(EnumPol.M);
		prviMenazder.setUloga(EnumUloga.MENADZER);
		prviMenazder.setKorisnickoIme("rade_420");

		//this.menadzerRep.save(prviMenazder);

		//Dodajem dostavljaca koji ce razneti porudzbine
		Dostavljac luka = new Dostavljac();
		luka.dodajPorudzbinu(prvaPorudzbina);
		luka.dodajPorudzbinu(drugaPorudzbina);

		luka.setIme("Luka");
		luka.setLozinka("*****l");
		luka.setKorisnickoIme("L__uka");
		luka.setPol(EnumPol.M);
		luka.setPrezime("Lukic");
		luka.setUloga(EnumUloga.DOSTAVLJAC);
		luka.setDatumRodjenja(datum);

		//this.dostavljacRep.save(luka);


	 */

	/*
		//Test stavke

		Artikal prviArtikal = new Artikal();
		prviArtikal.setCena(100);
		prviArtikal.setKolicina(100);
		prviArtikal.setTip(EnumTip.JELO);
		prviArtikal.setNaziv("Cokolada");
		prviArtikal.setOpis("Mlecna");

		Artikal drugiArtikal = new Artikal();
		drugiArtikal.setCena(200);
		drugiArtikal.setKolicina(300);
		drugiArtikal.setTip(EnumTip.JELO);
		drugiArtikal.setNaziv("Keks");
		drugiArtikal.setOpis("Integralni");

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

		StavkaPorudzbine stavka = new StavkaPorudzbine();
		stavka.setPorucenaKolicina(100);
		stavka.setArtikal(prviArtikal);

		StavkaPorudzbine stavka1 = new StavkaPorudzbine();
		stavka1.setArtikal(drugiArtikal);
		stavka1.setPorucenaKolicina(42);

		StavkaPorudzbine stavka2 = new StavkaPorudzbine();
		stavka2.setArtikal(prviArtikal);
		stavka2.setPorucenaKolicina(11);

		Set<StavkaPorudzbine> stavke = new HashSet<>();
		stavke.add(stavka);
		stavke.add(stavka1);
		stavke.add(stavka2);


		Porudzbina prvaPorudzbina = new Porudzbina();
		prvaPorudzbina.setRestoran(prviRestoran);
		//prvaPorudzbina.setKupac(mojKupac);
		prvaPorudzbina.setStatus(EnumStatus.ceka_dostavljaca);
		prvaPorudzbina.setCena(2000);
		prvaPorudzbina.setStavkePorudzbine(stavke);

		this.artikalRep.save(prviArtikal);
		this.artikalRep.save(drugiArtikal);
		this.lokacijaRep.save(prvaLokacija);
		this.restoranRep.save(prviRestoran);


		this.stavkaPorudzbineRep.save(stavka);
		this.stavkaPorudzbineRep.save(stavka1);
		this.stavkaPorudzbineRep.save(stavka2);

		this.porudzbinaRep.save(prvaPorudzbina);
	 */




		System.out.println("\nPROGRAM SE KOMPAJLIRA! \n\n");
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
