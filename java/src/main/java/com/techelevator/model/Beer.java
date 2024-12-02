package com.techelevator.model;

public class Beer {
    private int beerId;
    private int breweryId;
    private String beerName;
    private String beerType;
    private String description;
    private double abv;

    public Beer(int beerId, int breweryId, String beerName, String beerType, String description, double abv) {
        this.beerId = beerId;
        this.breweryId = breweryId;
        this.beerName = beerName;
        this.beerType = beerType;
        this.description = description;
        this.abv = abv;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }
}
