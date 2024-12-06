package com.techelevator.model;

public class ReviewDto {

    private String username;

    private Review review;




    public ReviewDto(String username, Review review) {
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
