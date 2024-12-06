package com.techelevator.dao;

import com.techelevator.model.Review;
import com.techelevator.model.ReviewDto;

import java.util.List;

public interface ReviewDao {

    Review addReview(Review review);

    Review getReviewById(int id);

    List<ReviewDto> getReviewsByBeerId(int beerId);

    void deleteReview(Review review);

}
