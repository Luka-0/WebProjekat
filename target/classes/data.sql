/*kupci*/
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac','MarkoM_12', '********', 'Marko', 'Markovic','M', '2004-02-10', 'KUPAC', 0);
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac', 'AleksaA_54', '******', 'Aleksa', 'Aleksic', 'M', '2002-02-10', 'KUPAC', 0);
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac', 'MMilic_980', '**********', 'Milica', 'Milic','Z' , '2005-02-10', 'KUPAC', 0);

/*dostavljaci*/
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_Ivanovic8', '**********', 'Ivan', 'Ivanovic','M' , '1995-02-10', 'DOSTAVLJAC');
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_AS_42', '**********', 'Aca', 'Stojanovic', 'M', '1995-02-10','DOSTAVLJAC');
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_KK_98', '**********', 'Kosta', 'Kostic', 'M', '1994-02-10','DOSTAVLJAC');

/*TipoviKupca*/
INSERT INTO TIP_KUPCA (ime,popust, trazeni_broj_bodova) VALUES('Zlatni',20,3);
INSERT INTO TIP_KUPCA (ime,popust, trazeni_broj_bodova) VALUES('Srebrni',30,5);
INSERT INTO TIP_KUPCA (ime,popust, trazeni_broj_bodova) VALUES('Bronzani',10,1);

/*lokacije*/
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Trg Mladenaca 11', 123, 232);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Maksima Gorkog 22', 223, 432);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Despota Stefana 7A', 1223, 2432);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Mise Dimitrijevica', 23, 42);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Dositeja Obradovica 4B', 293, 932);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Vuka Karadzica', 1, 3);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Bulevar Oslobodjenja 63a', 48, 49);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Bulevar oslobođenja 119,',90, 70);


/*restorani*/
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('BlueMoon','Restaurant & Bar', 2);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Atrijum','Studentski',5);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Centar 11','Italijanski', 1);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Joker', 'Palacinkarnica', 2);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Krilce i pivce', 'Brza hrana', 4);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Moskva', 'Ukrajinski', 6);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Richard Gyros ', 'Brza hrana', 7);
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('Caribic Pizza ', 'Brza hrana', 8);

/*Menadzeri*/
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, id_restorana) VALUES ('Menadzer','M_Arsa','jasamsifra123','Arsa','Arsic','Z','2001-12-02','MENADZER', 2);
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, id_restorana) VALUES ('Menadzer','L_Luka','nemamsifru','Luka','Stajic','Z','2002-03-03','MENADZER', 5);


/*Komentari*/
INSERT INTO KOMENTAR(ocena, tekst_komentara, id_kupca, id_restorana) VALUES(10, 'Dobar restoran', 1, 1);

/*Artikli*/
INSERT INTO ARTIKAL(cena, kolicina, naziv, opis, tip, id_restorana)VALUES(380,'GRAMI', 'Gyros','Greek Salad Gyros','JELO', 5);
INSERT INTO ARTIKAL(cena, kolicina, naziv, opis, tip, id_restorana)VALUES(120,'GRAMI', 'Pomfrit','porcija od 200g','JELO', 2);
INSERT INTO ARTIKAL(cena, kolicina, naziv, opis, tip, id_restorana)VALUES(530,'GRAMI', 'Rich premium tortilja','Gyros meso, dimljeni kačkavalj, povrće i kiporou salata','JELO', 7);
INSERT INTO ARTIKAL(cena, kolicina, naziv, opis, tip, id_restorana)VALUES(190,'GRAMI', 'Capricciosa ','Pizza','JELO', 8);
INSERT INTO ARTIKAL(cena, kolicina, naziv, opis, tip, id_restorana)VALUES(190,'MILILITRI', 'Veliki Shake','vanila, jagoda, čokolada, plazma','PICE', 8);

/*Komentari*/
INSERT INTO KOMENTAR(ocena, tekst_komentara, id_kupca, id_restorana) VALUES(10, 'Solidan restoran', 2, 1);
INSERT INTO KOMENTAR(ocena, tekst_komentara, id_kupca, id_restorana) VALUES(10, 'Sjajan restoran', 3, 2);



