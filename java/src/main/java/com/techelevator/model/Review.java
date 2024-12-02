package com.techelevator.model;

public class Review {
    private int reviewId;
    private int beerId;
    private int rating;
    private String username;
    private String review;

    public Review(int reviewId, int beerId, int rating, String username, String review) {
        this.reviewId = reviewId;
        this.beerId = beerId;
        this.rating = rating;
        this.username = username;
        this.review = review;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
