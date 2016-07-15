package com.endava.internship.rest.controller;

import com.endava.internship.rest.exception.ChampionshipDoesntExists;
import com.endava.internship.rest.model.Championship;
import com.endava.internship.rest.model.Club;
import com.endava.internship.rest.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController
@RequestMapping(path = "/championships",produces = MediaType.APPLICATION_JSON_VALUE)
public class ChampionshipController {
    @Autowired
    private ChampionshipService service;

//#####################GET


    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<Resource<Championship>>> championship() throws ChampionshipDoesntExists{



        Resources<Resource<Championship>> resources = Resources.wrap(service.getChampionships());
        resources.forEach(resource->{
            try {
                resource.add(linkTo(methodOn(ChampionshipController.class).championshipCountry(resource.getContent().getCountry())).withSelfRel());
            } catch (ChampionshipDoesntExists championshipDoesntExists) {
                championshipDoesntExists.printStackTrace();
            }
        });
        resources.add(linkTo(methodOn(ChampionshipController.class).championship()).withSelfRel());

        return new ResponseEntity(resources, HttpStatus.OK);
    }


    @RequestMapping(params = "club",method = RequestMethod.GET)
    public HttpEntity<Resources<Resource<Championship>>> getChampionships(@RequestParam("club") String name) throws ChampionshipDoesntExists {
        Resources<Resource<Championship>> resources=Resources.wrap(service.getChampionshipsByClubName(name));

        resources.forEach(e -> {
            try {
                e.add(linkTo(methodOn(ChampionshipController.class).
                        championshipCountry(e.getContent().getCountry())).withSelfRel());
            } catch (ChampionshipDoesntExists championshipDoesntExists) {
                championshipDoesntExists.printStackTrace();
            }
        });
        resources.add(linkTo(methodOn(ChampionshipController.class).championship()).withSelfRel());


        return new ResponseEntity(resources,HttpStatus.OK);

    }


    @RequestMapping(value = "/{championshipCountry}",method = RequestMethod.GET)
    public HttpEntity<Resource<Championship>> championshipCountry(@PathVariable("championshipCountry") String championship) throws ChampionshipDoesntExists{
        Championship c=service.getChampionshipByCountry(championship);
        if(c==null)throw new ChampionshipDoesntExists();
        Resource<Championship> champResource=new Resource<>(c,linkTo(methodOn(ChampionshipController.class).championship()).withRel("Championships"));
        return new ResponseEntity(champResource,HttpStatus.OK);
    }
//##################POST

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Championship>> championship(@RequestBody Championship c) throws ChampionshipDoesntExists {
        service.addChampionship(c);
        Resource<Championship> resource=new Resource<>(c);
        resource.add(linkTo(methodOn(ChampionshipController.class).championshipCountry(c.getCountry())).withSelfRel());
        return new ResponseEntity(resource, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{championshipCountry}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Championship>> championshipAdd(@RequestBody Championship c,@PathVariable String championship) throws ChampionshipDoesntExists {
        c.setName(championship);
        service.updateOrCreateChampionshipDetails(c);
        Resource<Championship> resource=new Resource<>(c);
        resource.add(linkTo(methodOn(ChampionshipController.class).championshipCountry(c.getCountry())).withSelfRel());
        return new ResponseEntity(resource, HttpStatus.OK);
    }

//############################PUT

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody HttpEntity<Resources<Resource<Championship>>> championshipCountryPut(@RequestBody List<Championship> c) throws ChampionshipDoesntExists {
        service.setList(c);
        Resources<Resource<Championship>> resources=Resources.wrap(c);
        resources.forEach(resource->{
            try {
                resource.add(linkTo(methodOn(ChampionshipController.class).championshipCountry(resource.getContent().getCountry())).withSelfRel());
            } catch (ChampionshipDoesntExists championshipDoesntExists) {
                championshipDoesntExists.printStackTrace();
            }
        });

        resources.add(linkTo(methodOn(ChampionshipController.class).championship()).withSelfRel());


        return new ResponseEntity(resources, HttpStatus.OK);
    }


    @RequestMapping(value = "/{championshipCountry}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<Resource<Championship>> championshipCountryReplace(@PathVariable String championship,@RequestBody Championship c) throws ChampionshipDoesntExists {
        c.setCountry(championship);
        service.replaceOrCreateChampionship(c);
        Resource resource=new Resource(c,linkTo(methodOn(ChampionshipController.class).championship()).withSelfRel());
        return new ResponseEntity<Resource<Championship>>(resource,HttpStatus.OK);
    }

//########################DELETE

    @RequestMapping(method = RequestMethod.DELETE)
    public HttpEntity deleteChamps(){
        service.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/{championshipCountry}",method = RequestMethod.DELETE)
    public HttpEntity deleteChampsByCountry(@PathVariable("championshipCountry") String country){
        service.deleteChampionshipByCountry(country);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}


