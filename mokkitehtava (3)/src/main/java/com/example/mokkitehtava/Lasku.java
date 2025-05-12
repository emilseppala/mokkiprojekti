package com.example.mokkitehtava;

import java.time.LocalDate;

public class Lasku {
    private String mokki;
    private String asiakas;
    private LocalDate alkuPaiva;
    private LocalDate loppuPaiva;
    private double hinta;
    private boolean maksettu;

    public Lasku(String mokki, String asiakas, LocalDate alkuPaiva, LocalDate loppuPaiva, double hinta) {
        this.mokki = mokki;
        this.asiakas = asiakas;
        this.alkuPaiva = alkuPaiva;
        this.loppuPaiva = loppuPaiva;
        this.hinta = hinta;
        this.maksettu = false;
    }

    public String getMokki() { return mokki; }
    public String getAsiakas() { return asiakas; }
    public LocalDate getAlkuPaiva() { return alkuPaiva; }
    public LocalDate getLoppuPaiva() { return loppuPaiva; }
    public double getHinta() { return hinta; }
    public boolean isMaksettu() { return maksettu; }
    public void setMaksettu(boolean maksettu) { this.maksettu = maksettu; }
}