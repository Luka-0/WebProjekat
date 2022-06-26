package com.example.demo.dto;


import com.example.demo.entity.EnumStatusRestorana;
import com.example.demo.entity.Lokacija;
import com.example.demo.entity.Restoran;


public class PretragaDto {

    private String naziv;

    private String tipRestorana;

    private String adresaLokacije;

    private EnumStatusRestorana statusRestorana;

    public PretragaDto(){}

    public PretragaDto(String naziv, String tipRestorana, String adresaLokacije, EnumStatusRestorana statusRestorana) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.adresaLokacije = adresaLokacije;
        this.statusRestorana = statusRestorana;
    }

    public PretragaDto(Restoran restoran){
        this.naziv = restoran.getNaziv();
        this.tipRestorana = restoran.getTipRestorana();
        this.adresaLokacije = restoran.getLokacija().getAdresa();
        this.statusRestorana = restoran.getStatusRestorana();
    }

    public EnumStatusRestorana getStatusRestorana() {
        return statusRestorana;
    }

    public void setStatusRestorana(EnumStatusRestorana statusRestorana) {
        this.statusRestorana = statusRestorana;
    }

    public String getNaziv() {  return  naziv;  }
    public void setNaziv(String naziv) {  this.naziv = naziv;   }

    public String getTipRestorana() {  return  tipRestorana;  }
    public void setTipRestorana(String tip){  this.tipRestorana = tip;  }

    public String getAdresaLokacije() { return adresaLokacije;  }

    public void setAdresaLokacije(String adresaLokacije) {  this.adresaLokacije = adresaLokacije;   }
}
