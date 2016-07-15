package com.endava.internship.rest.repository;

import com.endava.internship.rest.model.Championship;
import com.endava.internship.rest.model.Club;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChampionshipRepository {
    private List<Championship> list;

    public List getChampionships() {
        return list;
    }


    @PostConstruct
    public void settingList() {
        list = new ArrayList<>();
        List<Club> clubs = new ArrayList<>();
        clubs.add(new Club("PSG", 25000, 23));
        clubs.add(new Club("Lille", 29000, 20));

        list.add(new Championship("Ligue 1", 24, "France", clubs));
        list.add(new Championship("Liga 1", 16, "Roumanie"));
    }

    public Championship getChampionship(String country) {
        return list.stream().filter(e -> country.equals(e.getCountry())).findFirst().orElse(null);
    }

    public void addChampionship(Championship c) {
        list.add(c);
    }

    public void setList(List<Championship> list) {
        this.list = list;
    }

    public void replaceChampionships(Championship c) {
        list.stream().forEach(e -> {
            if (e.getCountry().equals(c.getCountry())) {
                int index = list.indexOf(e);
                list.remove(e);
                list.add(index, c);
            }
        });
    }

    public void deleteAll() {
        list = new ArrayList<>();
    }

    public void removeChampionship(String country) {
        list.remove(list.stream().filter(c -> country.equals(c.getCountry())).findFirst().orElse(null));
    }

    public List<Championship> getChampionshipsByClubName(String name) {
        Object o =  list.stream().filter(e -> {
            if (e.getClubs() != null && e.getClubs().stream().
                    filter(c -> c.getName().contains(name)).findAny().orElse(null) != null) return true;
            else return false;
        }).
                findAny().orElse(null) ;

        List list=new ArrayList();

        if(o instanceof Championship){
            list.add(o);
        }else{
            list= (List) o;
        }

        if(list==null)return new ArrayList<>();
        return list;
    }
}
