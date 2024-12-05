package com.techelevator.controller;

import com.techelevator.dao.BreweryDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin

public class BreweryController {


    private BreweryDao breweryDao;


    @Autowired
    public BreweryController(BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/breweries/add", method = RequestMethod.POST)
    public Brewery addBrewery( @RequestBody Brewery brewery){
        try{

            if (breweryDao.getBreweryByName(brewery.getBreweryName()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brewery already exists.");
            } else {
                return breweryDao.addBrewery(brewery);
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add brewery.");
        }
    }


    @RequestMapping( path ="/breweries", method = RequestMethod.GET)
    public List<Brewery> getBreweries(){
        try{
            return breweryDao.getBreweries();
        } catch (DaoException e){
            throw new DaoException("Unable to retrieve Breweries", e);
        }
    }

    @RequestMapping(path = "/breweries/{id}", method= RequestMethod.GET)
    public Brewery getBrewery(@PathVariable int id){
        try{
            Brewery brewery = breweryDao.getBreweryById(id);
            if(brewery == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brewery not found.");
            } else {
                return brewery;
            }
        } catch (DaoException e){
            throw new DaoException("Unable to retrieve Brewery", e);
        }
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'BREWER')")
    @RequestMapping(path = "/breweries/{id}/update", method= RequestMethod.PUT)
    public void updateBrewery (@PathVariable int id, @RequestBody Brewery brewery){

        if(brewery.getBreweryId() ==id) {
            try {
                boolean isUpdated = breweryDao.updateBrewery(brewery);
                if (!isUpdated) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brewery not updated");
                }
            } catch (DaoException e) {
                throw new DaoException("Unable to retrieve Brewery", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BreweryID does not match existing brewery");
        }
    }






}
