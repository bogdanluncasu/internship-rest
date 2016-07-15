package com.endava.internship.rest.model;

/**
 * Created by bluncasu on 7/15/2016.
 */
public class Player {
    private String nume;

    private int numar;

    public Player(String nume, int numar) {
        this.nume = nume;
        this.numar = numar;
    }

    public Player(){}


    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


}
