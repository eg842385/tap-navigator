package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Beer;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBeerDao implements BeerDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Beer> getBeers(int breweryId) {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT beer_id, brewery_id, name, beer_type, description, abv, img\n" +
                "\tFROM beer WHERE brewery_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, breweryId);
            while (results.next()){
                Beer beer = mapRowToBeer(results);
                beers.add(beer);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return beers;
    }

    @Override
    public Beer getBeerByName(String beerName) {
        Beer beer = null;
        String sql = "SELECT beer_id, brewery_id, name, beer_type, description, abv, img FROM beer WHERE name = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerName);
            if (results.next()){
                beer = mapRowToBeer(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return beer;
    }

    @Override
    public Beer getBeerById(int beerId) {
        Beer beer = null;
        String sql = "SELECT beer_id, brewery_id, name, beer_type, description, abv, img FROM beer WHERE beer_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, beerId);
            if (results.next()){
                beer = mapRowToBeer(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return beer;
    }

    @Override
    public Beer addBeer(Beer beer) {
        Beer newBeer = null;
        String sql = "INSERT INTO beer (brewery_id, name, beer_type, description, abv, img) VALUES (?, (LOWER(TRIM(?))), (LOWER(TRIM(?))), ?, ?, ?) RETURNING beer_id";
        try{
            int newBeerId = jdbcTemplate.queryForObject(sql, Integer.class, beer.getBreweryId(),
                    beer.getBeerName(), beer.getBeerType(), beer.getDescription(), beer.getAbv(), beer.getImg());
            newBeer = getBeerById(newBeerId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newBeer;
    }

    @Override
    public Boolean updateBeer(Beer beer){

        String sql = "UPDATE beer\n" +
                "\tSET  brewery_id=?, name=?, beer_type=?, description=?, abv=?, img=?\n" +
                "\tWHERE beer_id=?;";
        try{
            int rowsUpdate = jdbcTemplate.update(sql, beer.getBreweryId(), beer.getBeerName(), beer.getBeerType(), beer.getDescription(), beer.getAbv(), beer.getImg(), beer.getBeerId());
            return rowsUpdate > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e) { throw new DaoException("Data integrity violation", e);
        }

    }

    @Override
    public Boolean deleteBeerById(int id){
        String deleteReviewSql = "DELETE FROM reviews\n" +
                "\tWHERE beer_id=?;";
        String deleteBeerSql = "DELETE FROM beer\n" +
                "\tWHERE beer_id=?;";
        try{
            int rowsDeletedFromReview = jdbcTemplate.update(deleteReviewSql, id);
            int rowsDeletedFromBeer = jdbcTemplate.update(deleteBeerSql, id);
            return (rowsDeletedFromReview > 0) && (rowsDeletedFromBeer > 0) ;
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }catch (DataIntegrityViolationException e) { throw new DaoException("Data integrity violation", e);
        }



    }

    private Beer mapRowToBeer(SqlRowSet rs){
        Beer beer = new Beer();
        beer.setBeerId(rs.getInt("beer_id"));
        beer.setBreweryId(rs.getInt("brewery_id"));
        beer.setBeerName(rs.getString("name"));
        beer.setBeerType(rs.getString("beer_type"));
        beer.setDescription(rs.getString("description"));
        beer.setAbv(rs.getDouble("abv"));
        beer.setImg(rs.getString("img"));
        return beer;
    }

}
