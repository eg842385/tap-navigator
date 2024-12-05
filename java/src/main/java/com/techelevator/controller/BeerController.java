package com.techelevator.controller;

import com.techelevator.dao.BeerDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class BeerController {

    private BeerDao beerDao;

    @Autowired
    public BeerController(BeerDao beerDao){this.beerDao = beerDao;}

    @PreAuthorize("hasAnyRole('ADMIN', 'BREWER')")
    @RequestMapping(path = "/breweries/{id}/beers/add", method = RequestMethod.POST)
    public Beer addBeer(@RequestBody Beer beer){
        try{
            if(beerDao.getBeerByName(beer.getBeerName()) != null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Beer already exists.");
            } else {
                return beerDao.addBeer(beer);
            }
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add brewery.");
        }
    }

    @RequestMapping(path = "/breweries/{id}/beers", method = RequestMethod.GET)
    public List<Beer> getBeers(@PathVariable int id){
        try{
            return beerDao.getBeers(id);
        } catch (DaoException e){
            throw new DaoException("Unable to retrieve Breweries", e);
        }
    }

    @RequestMapping(path = "/breweries/{id}/beers/{beerId}", method = RequestMethod.GET)
    public Beer getBeer(@PathVariable("beerId") int beerId){
        try{
            Beer beer = beerDao.getBeerById(beerId);
            if(beer == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beer not found.");
            } else {
                return beer;
            }

        } catch (DaoException e){
            throw new DaoException("Unable to retrieve Brewery", e);
        }
    }






}
