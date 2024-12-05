package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Brewery;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryDao implements BreweryDao{
    
    private final JdbcTemplate jdbcTemplate;
    
    public JdbcBreweryDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Brewery> getBreweries() {

        List<Brewery> breweries = new ArrayList<>();
        String sql = "SELECT brewery_id, name, user_id, description, address, city, state, zipcode\n" +
                "\tFROM brewery";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Brewery brewery = mapRowToBrewery(results);
                breweries.add(brewery);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return breweries;

    }



    @Override
    public Brewery addBrewery(Brewery brewery) {
        Brewery newBrewery = null; 
        String insertBrewerySql = "INSERT INTO brewery (user_id, name, description, address, city, state, zipcode) values (?, (LOWER(TRIM(?))), ?, ?, ?, ?, ?) RETURNING brewery_id";
        try{
            int newBreweryId = jdbcTemplate.queryForObject(insertBrewerySql, int.class, brewery.getUserId(), brewery.getBreweryName(), brewery.getDescription(), brewery.getAddress(), brewery.getCity(), brewery.getState(), brewery.getZipcode());
            newBrewery = getBreweryById(newBreweryId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newBrewery;
    }

    @Override
    public Brewery getBreweryById(int id) {
        Brewery brewery = null;
        String sql = "SELECT brewery_id, name, user_id, description, address, city, state, zipcode\n" +
                "\tFROM brewery WHERE brewery_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()){
                brewery = mapRowToBrewery(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return brewery;
    }

    @Override
    public Brewery getBreweryByName(String name) {
        Brewery brewery = null;
        String sql = "SELECT brewery_id, name, user_id, description, address, city, state, zipcode\n" +
                "\tFROM brewery WHERE name = (LOWER(TRIM(?)));";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()){
                brewery = mapRowToBrewery(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return brewery;
    }

    @Override
    public Brewery searchBreweryByName(String name) {
        Brewery brewery = null;
        String sql = "SELECT brewery_id, name, user_id, description, address, city, state, zipcode\n" +
                "\tFROM brewery WHERE name ILIKE '%?%';";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            if (results.next()){
                brewery = mapRowToBrewery(results);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return brewery;
    }

    @Override
    public boolean updateBrewery(Brewery brewery) {

        String sql =" UPDATE brewery\n" +
                "\tSET user_id=?, name=?, description=?, address=?, city=?, state=?, zipcode=?\n" +
                "\tWHERE brewery_id=?;";
                try {
                   int rowsUpdated = jdbcTemplate.update(sql,
                           brewery.getUserId(), brewery.getBreweryName(), brewery.getDescription(), brewery.getAddress(), brewery.getCity(), brewery.getState(), brewery.getZipcode(),brewery.getBreweryId());
                    return rowsUpdated >0;

                } catch (CannotGetJdbcConnectionException e) {
                    throw new DaoException("Unable to connect to server or database", e);
                }catch (DataIntegrityViolationException e) { throw new DaoException("Data integrity violation", e);
                }


    }







    private Brewery mapRowToBrewery(SqlRowSet rs) {
        Brewery brewery = new Brewery();
        brewery.setBreweryId(rs.getInt("brewery_id"));
        brewery.setBreweryName(rs.getString("name"));
        brewery.setUserId(rs.getInt("user_id"));
        brewery.setDescription(rs.getString("description"));
        brewery.setAddress(rs.getString("address"));
        brewery.setCity(rs.getString("city"));
        brewery.setState(rs.getString("state"));
        brewery.setZipcode(rs.getInt("zipcode"));
        return brewery;
    }
    
    
    
    
    
}

