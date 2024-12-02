package com.techelevator.model;

public class Brewery {
    private int breweryId;
    private int userId;
    private String breweryName;

    public Brewery(int breweryId, int userId, String breweryName) {
        this.breweryId = breweryId;
        this.userId = userId;
        this.breweryName = breweryName;
    }

    public Brewery() {

    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }
}
