package com.endava.internship.rest.controller;

import com.endava.internship.rest.exception.ChampionshipDoesntExists;
import com.endava.internship.rest.exception.InvalidChampionshipDeclaration;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ErrorHandlingController implements ErrorController {

    @RequestMapping(path = "/error")
    public HttpEntity<Resource<String>> handleError() {
        return new ResponseEntity<>(new Resource<>("Resource not found"), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(ChampionshipDoesntExists.class)
    public HttpEntity<Resource<String>> handleChampionshipDoesntExistsException() {
        return new ResponseEntity<>(new Resource<>("Championship Doesn't Exists"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidChampionshipDeclaration.class)
    public HttpEntity<Resource<String>> handleInvalidChampionshipDeclarationException() {
        return new ResponseEntity<>(new Resource<>("Invalid Championship Declaration"), HttpStatus.NOT_FOUND);
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}