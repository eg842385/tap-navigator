package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.ReviewDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review addReview(Review review) {
        Review newReview = null;
        String sql ="INSERT INTO reviews(\n" +
                "\t beer_id, rating, review, user_id)\n" +
                "\tVALUES ( ?, ?, ?, ?) RETURNING review_id;";
        try{
            int newReviewId = jdbcTemplate.queryForObject(sql, int.class, review.getBeerId(),review.getRating(), review.getReview(), review.getUserId());
            newReview = getReviewById(newReviewId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newReview;
    }

    @Override
    public Review getReviewById(int id){
        Review review = null;
        String sql = "SELECT review_id, beer_id, rating, review, user_id\n" +
                "\tFROM reviews WHERE review_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                review = mapRowToReview(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return review;
    }

    @Override
    public List<ReviewDto> getReviewsByBeerId(int beerId) {

        List<ReviewDto> allReviewDto = new ArrayList<>();

        String sql = "SELECT r.review_id, r.beer_id, r.rating, r.review, r.user_id, u.username "
                + "FROM reviews r "
                + "JOIN users u ON r.user_id = u.user_id "
                + "WHERE r.beer_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            while (results.next()) {
                Review review = mapRowToReview(results);
                String username = results.getString("username");
                ReviewDto reviewDto = new ReviewDto(username, review);
                allReviewDto.add(reviewDto);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return allReviewDto;
    }


    @Override
    public void deleteReview(Review review) {

    }

    private Review mapRowToReview(SqlRowSet rs){
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setBeerId(rs.getInt("beer_id"));
        review.setRating(rs.getInt("rating"));
        review.setUserId(rs.getInt("user_id"));
        review.setReview(rs.getString("review"));
        return review;
    }

   private String mapRowToUsername(SqlRowSet rs){
       return rs.getString("username");
   }

}
