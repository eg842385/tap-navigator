package com.techelevator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techelevator.service.BreweryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BreweryApiController {

    private final BreweryApiService breweryApiService;

    @Autowired
    public BreweryApiController(BreweryApiService breweryApiService) {
        this.breweryApiService = breweryApiService;
    }

    @RequestMapping(path = "/breweries/fetch", method = RequestMethod.GET)
    public void fetchBreweries() throws JsonProcessingException {
        breweryApiService.fetchAndSaveBreweries();
    }
}
