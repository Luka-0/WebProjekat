/*lokacije*/
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Trg Mladenaca 11', 123, 232);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Maksima Gorkog 22', 223, 432);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Despota Stefana 7A', 1223, 2432);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Mise Dimitrijevica', 23, 42);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Dositeja Obradovica 4B', 293, 932);
INSERT INTO LOKACIJA(adresa, geografska_duzina,geografska_sirina) VALUES ( 'Vuka Karadzica', 1, 3);

/*restorani*/
INSERT INTO RESTORAN(naziv, tip_restorana, id_lokacije) VALUES ('BlueMoon','Restaurant & Bar', 2);

/*kupci*/
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac','MarkoM_12', '********', 'Marko', 'Markovic','M', '2004-02-10', 'KUPAC', 0);
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac', 'AleksaA_54', '******', 'Aleksa', 'Aleksic', 'M', '2002-02-10', 'KUPAC', 0);
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, broj_sakupljenih_bodova) VALUES ('Kupac', 'MMilic_980', '**********', 'Milica', 'Milic','Z' , '2005-02-10', 'KUPAC', 0);
/*dostavljaci*/
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_Ivanovic8', '**********', 'Ivan', 'Ivanovic','M' , '1995-02-10', 'DOSTAVLJAC');
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_AS_42', '**********', 'Aca', 'Stojanovic', 'M', '1995-02-10','DOSTAVLJAC');
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga) VALUES ('Dostavljac', 'Dostava_KK_98', '**********', 'Kosta', 'Kostic', 'M', '1994-02-10','DOSTAVLJAC');
/*Menadzeri
INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga,id_restorana) VALUES ('Menadzer', 'MM_SS_78', '******', 'Stefan', 'Stefanovic', 'M', '1994-02-10','MENADZER',1);
*/

INSERT INTO KOMENTAR(ocena, tekst_komentara, id_kupca, id_restorana) VALUES(10, 'Dobar restoran', 1, 1);

INSERT INTO KORISNIK (dtype, korisnicko_ime, lozinka,ime,prezime, pol, datum_rodjenja,uloga, id_restorana) VALUES ('Menadzer','M_Arsa','jasamsifra123','Arsa','Arsic','Z','2001-02-02','MENADZER', 1);