package com.techelevator.controller;

import com.techelevator.dao.ReviewDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ReviewController {

    private ReviewDao reviewDao;

    @Autowired
    public ReviewController(ReviewDao reviewDao){this.reviewDao = reviewDao;}

    @RequestMapping(path = "/breweries/{id}/beers/{beerId}/reviews", method = RequestMethod.POST)
    public Review addReview(@RequestBody Review review){
        try{
            return reviewDao.addReview(review);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add review.");
        }
    }

    @RequestMapping(path = "/breweries/{id}/beers/{beerId}/reviews", method = RequestMethod.GET)
    public List<ReviewDto> getReviewsByBeerId(@PathVariable("beerId") int beerId){
        try{
            return reviewDao.getReviewsByBeerId(beerId);
        } catch (DaoException e){
            throw new DaoException("Unable to retrieve Reviews", e);
        }


    }



}
