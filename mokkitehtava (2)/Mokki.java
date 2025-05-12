package com.example.mokkitehtava;

import javafx.beans.property.*;

public class Mokki {

    private StringProperty nimi;
    private StringProperty osoite;
    private DoubleProperty hinta;
    private DoubleProperty koko;
    private BooleanProperty onkopalju;

    public Mokki(String nimi, String osoite, Double hinta, Double koko, boolean onkopalju) {
        this.nimi = new SimpleStringProperty(nimi);
        this.osoite = new SimpleStringProperty(osoite);
        this.hinta = new SimpleDoubleProperty(hinta);
        this.koko = new SimpleDoubleProperty(koko);
        this.onkopalju = new SimpleBooleanProperty(onkopalju);
    }

    public String getNimi() {
        return nimi.get();
    }
    public void setNimi(String nimi) {
        this.nimi.set(nimi);
    }
    public String getOsoite() {
        return osoite.get();
    }
    public void setOsoite(String osoite) {
        this.osoite.set(osoite);
    }
    public Double getHinta() {
        return hinta.get();
    }
    public void setHinta(Double hinta) {
        this.hinta.set(hinta);
    }
    public Double getKoko() {
        return koko.get();
    }
    public void setKoko(Double koko) {
        this.koko.set(koko);
    }
    public Boolean getOnkopalju() {
        return onkopalju.get();
    }
    public void setOnkopalju(Boolean onkopalju) {
        this.onkopalju.set(onkopalju);
    }

    public StringProperty nimiProperty() {
        return nimi;
    }
    public StringProperty osoiteProperty() {
        return osoite;
    }
    public DoubleProperty hintaProperty() {
        return hinta;
    }
    public DoubleProperty kokoProperty() {
        return koko;
    }
    public BooleanProperty onkopaljuProperty() {
        return onkopalju;
    }

    public String toString(){
        return nimi.get();
    }


}
