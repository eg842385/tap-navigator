package com.techelevator.model;

public class Review {
    private int reviewId;
    private int beerId;
    private int rating;
    private int userId;
    private String review;

    public Review(){

    }

    public Review(int reviewId, int beerId, int rating, int userId, String review) {
        this.reviewId = reviewId;
        this.beerId = beerId;
        this.rating = rating;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
