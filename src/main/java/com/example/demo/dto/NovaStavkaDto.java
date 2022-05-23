package com.example.demo.dto;

public class NovaStavkaDto {
    private long idArtikla;
    private int porucenaKolicina;

    public NovaStavkaDto(){}

    public NovaStavkaDto(long idArtikla, int porucenaKolicina) {
        this.idArtikla = idArtikla;
        this.porucenaKolicina = porucenaKolicina;
    }

    public long getIdArtikla() {
        return idArtikla;
    }

    public void setIdArtikla(long idArtikla) {
        this.idArtikla = idArtikla;
    }

    public int getPorucenaKolicina() {
        return porucenaKolicina;
    }

    public void setPorucenaKolicina(int porucenaKolicina) {
        this.porucenaKolicina = porucenaKolicina;
    }

    @Override
    public String toString() {
        return "NovaStavkaDto{" +
                "idArtikla=" + idArtikla +
                ", porucenaKolicina=" + porucenaKolicina +
                '}';
    }
}
