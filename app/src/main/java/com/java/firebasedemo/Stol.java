package com.java.firebasedemo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Stol {
    private String ime_adimnaSobe;
    private  String ulaganje;
    private String broj_igraca;

    public Stol() {
    }

    public Stol(String ime_adimnaSobe, String ulaganje, String broj_igraca) {
        this.ime_adimnaSobe = ime_adimnaSobe;
        this.ulaganje = ulaganje;
        this.broj_igraca = broj_igraca;
    }

    public String getIme_adimnaSobe() {
        return ime_adimnaSobe;
    }

    public String getUlaganje() {
        return ulaganje;
    }

    public String getBroj_igraca() {
        return broj_igraca;
    }
}
