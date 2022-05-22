package com.example.demo.dto;

public class IzmenaPoruceneKolicineDto {
    private long idStavkeKojaSeMenja;
    private int novaPorucenaKolicina;

    public IzmenaPoruceneKolicineDto(){}

    public IzmenaPoruceneKolicineDto(long idStavkeKojaSeMenja, int novaPorucenaKolicina) {
        this.idStavkeKojaSeMenja = idStavkeKojaSeMenja;
        this.novaPorucenaKolicina = novaPorucenaKolicina;
    }

    public long getIdStavkeKojaSeMenja() {
        return idStavkeKojaSeMenja;
    }

    public void setIdStavkeKojaSeMenja(long idStavkeKojaSeMenja) {
        this.idStavkeKojaSeMenja = idStavkeKojaSeMenja;
    }

    public int getNovaPorucenaKolicina() {
        return novaPorucenaKolicina;
    }

    public void setNovaPorucenaKolicina(int novaPorucenaKolicina) {
        this.novaPorucenaKolicina = novaPorucenaKolicina;
    }

    @Override
    public String toString() {
        return "IzmenaPoruceneKolicineDto{" +
                "idStavkeKojaSeMenja=" + idStavkeKojaSeMenja +
                ", novaPorucenaKolicina=" + novaPorucenaKolicina +
                '}';
    }
}
