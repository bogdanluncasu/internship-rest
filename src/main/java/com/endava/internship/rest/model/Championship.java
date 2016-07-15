package com.endava.internship.rest.model;

import java.util.List;

/**
 * Created by bluncasu on 7/15/2016.
 */
public class Championship {
    private String name;
    private String country;
    private int teams;
    private List<Club> clubs;

    public Championship(String name,  int teams,String country, List<Club> clubs) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.clubs = clubs;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }



    public Championship(){};
    public Championship(String name,int teams,String country){
        this.name=name;
        this.teams=teams;
        this.country=country;
    };
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeams() {
        return teams;
    }

    public void setTeams(int teams) {
        this.teams = teams;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
