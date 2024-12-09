package com.techelevator.dao;

import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDao {

    List<Brewery> getBreweries();

    Brewery addBrewery(Brewery brewery);

    Brewery addBreweryFromAPI(Brewery brewery);

    Brewery getBreweryById(int id);

    Brewery getBreweryByName(String name);

    Brewery searchBreweryByName(String name);

    boolean updateBrewery(Brewery brewery);


}
