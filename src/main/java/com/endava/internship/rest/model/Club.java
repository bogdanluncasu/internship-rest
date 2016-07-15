package com.endava.internship.rest.model;

import java.util.List;

/**
 * Created by bluncasu on 7/15/2016.
 */
public class Club {
    private String name;
    private int budget;
    private int players;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    private List<Player> playerList;

    public Club(String name, int budget, int players) {
        this.name = name;
        this.budget = budget;
        this.players = players;
    }
    public Club(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
