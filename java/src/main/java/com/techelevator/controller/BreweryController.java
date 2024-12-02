package com.techelevator.controller;

import com.techelevator.dao.BreweryDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Brewery;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class BreweryController {

    private final BreweryDao breweryDao;



    public BreweryController(BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    @RequestMapping(path = "/addbrewery", method = RequestMethod.POST)
    public void addBrewery(@Valid @RequestBody Brewery brewery){
        try{

            if (breweryDao.getBreweryByName(brewery.getBreweryName()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brewery already exists.");
            } else {
                breweryDao.addBrewery(brewery);
            }
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add brewery.");
        }
    }


}
