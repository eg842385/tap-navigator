package com.techelevator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.BreweryDao;
import com.techelevator.model.Brewery;
import com.techelevator.model.BreweryApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class BreweryApiService {

    private final RestTemplate restTemplate;
    private final BreweryDao breweryDao;

    @Autowired
    public BreweryApiService(RestTemplate restTemplate, BreweryDao breweryDao) {
        this.restTemplate = restTemplate;
        this.breweryDao = breweryDao;
    }

    @PostConstruct
    public void initialize(){
        fetchAndSaveBreweries();
    }


    public void fetchAndSaveBreweries() {
        int page = 1;
        int perPage = 50;
        boolean hasMorePages = true;

        while(hasMorePages){
            String url = "https://api.openbrewerydb.org/v1/breweries?by_state=ohio&page=" + page + "&per_page=" + perPage;
            System.out.println("Fetching from URL: " + url);


            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            System.out.println("HTTP Status: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders());

            String jsonResponse = response.getBody();
            System.out.println("Raw JSON Response: " + jsonResponse);

            if (response.getStatusCode() == HttpStatus.OK && jsonResponse != null && !jsonResponse.isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {

                    BreweryApiResponseDto[] breweries = objectMapper.readValue(jsonResponse, BreweryApiResponseDto[].class);
                    System.out.println("Mapped Breweries: " + breweries.length);

                    for (BreweryApiResponseDto apiBrewery : breweries) {
                        System.out.println("Brewery Name: " + apiBrewery.getName());
                        Brewery brewery = mapApiToModel(apiBrewery);
                        breweryDao.addBreweryFromAPI(brewery);
                    }

                    hasMorePages = breweries.length == perPage;
                    page++;
                } catch (Exception e) {
                    e.printStackTrace();
                    hasMorePages = false;
                }
            } else {
                System.out.println("Failed to fetch data from API or response is empty.");
                hasMorePages = false;
            }
        }
    }


    private Brewery mapApiToModel(BreweryApiResponseDto apiBrewery) {
        Brewery brewery = new Brewery();


        String breweryName = apiBrewery.getName();
        if (breweryName == null || breweryName.trim().isEmpty()) {
            breweryName = "Unknown Brewery Name";
        }
        brewery.setBreweryName(breweryName);


        String description = apiBrewery.getBreweryType();
        if (description == null || description.trim().isEmpty()) {
            description = "No description available";
        }
        brewery.setDescription(description);


        String address = apiBrewery.getStreet();
        if (address == null || address.trim().isEmpty()) {
            address = "No street address provided";
        }
        brewery.setAddress(address);


        String city = apiBrewery.getCity();
        if (city == null || city.trim().isEmpty()) {
            city = "Unknown City";
        }
        brewery.setCity(city);
        
        String stateProvince = apiBrewery.getStateProvince();
        if (stateProvince == null || stateProvince.trim().isEmpty()) {
            stateProvince = "Unknown State/Province";
        }
        brewery.setState(stateProvince);

        String postalCode = apiBrewery.getPostalCode();
        int zipcode = 0;
        if (postalCode != null && !postalCode.trim().isEmpty()) {
            String appendedZipcode = postalCode.substring(0,5);
            try {
                zipcode = Integer.parseInt(appendedZipcode);
            } catch (NumberFormatException e) {
                zipcode = 0;
            }
        }
        brewery.setZipcode(zipcode);

        brewery.setImg("https://images.pexels.com/photos/274194/pexels-photo-274194.jpeg?auto=compress&cs=tinysrgb&w=600");

        return brewery;
    }


}
