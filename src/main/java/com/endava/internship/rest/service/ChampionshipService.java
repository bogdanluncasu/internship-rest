package com.endava.internship.rest.service;

import com.endava.internship.rest.exception.InvalidChampionshipDeclaration;
import com.endava.internship.rest.model.Championship;
import com.endava.internship.rest.model.Club;
import com.endava.internship.rest.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by bluncasu on 7/15/2016.
 */
@Service
public class ChampionshipService {
    @Autowired
    private ChampionshipRepository repository;

    public void addChampionship(Championship c) {
        if (getChampionshipByCountry(c.getCountry()) != null || c.getName().trim().length() == 0)
            throw new InvalidChampionshipDeclaration();

        repository.addChampionship(c);

    }

    public List getChampionships() {
        return repository.getChampionships();
    }

    public Championship getChampionshipByCountry(String country) {
        return repository.getChampionship(country);
    }

    public void updateOrCreateChampionshipDetails(Championship c) {
        if (c.getName().trim().length() == 0) throw new InvalidChampionshipDeclaration();

        Championship champ = getChampionshipByCountry(c.getCountry());
        if (champ == null) repository.addChampionship(c);
        else {
            champ.setCountry(c.getCountry());
            champ.setTeams(c.getTeams());
            champ.setClubs(c.getClubs());
            champ.setName(c.getName());
        }
    }

    public void setList(List<Championship> list) {
        list.forEach(c -> {
            if (c.getName().trim().length() == 0) throw new InvalidChampionshipDeclaration();
        });
        repository.setList(list);
    }

    public void replaceOrCreateChampionship(Championship c) {
        if (c.getName().trim().length() == 0) throw new InvalidChampionshipDeclaration();
        Championship champ=getChampionshipByCountry(c.getCountry());
        if(champ==null)repository.addChampionship(c);
        else repository.replaceChampionships(c);

    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteChampionshipByCountry(String country) {
        if(getChampionshipByCountry(country)==null)
            return;
        repository.removeChampionship(country);
    }

    public  List<Championship> getChampionshipsByClubName(String name) {
        return repository.getChampionshipsByClubName(name);
    }
}
