package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {

    List<Beer> getBeers(int breweryId);

    Beer getBeerByName(String beerName);

    Beer getBeerById(int beerId);

    Beer addBeer(Beer beer);

    Boolean updateBeer(Beer beer);

    Boolean deleteBeerById(int id);
}
