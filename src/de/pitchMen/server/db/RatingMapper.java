package de.pitchMen.server.db;

import java.sql.*;
import java.util.Vector;


/**
 * Bildet Raiting-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author
 */

public class RatingMapper {

    /**
     * Fügt ein Rating-Objekt der Datenbank hinzu.
     * 
     * @param rating 
     * @return
     */
    public Rating insert(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * Aktuallisiert ein Rating-Objekt in der Datenbank.
     * 
     * @param rating 
     * @return
     */
    public Rating update(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * Löscht ein Rating-Objekt aus der Datenbank.
     * 
     * @param rating 
     * @return
     */
    public void delete(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * Findet ein Rating-Objekt anhand der übergebenen Id in der Datenbank.
     * 
     * @param id 
     * @return
     */
    public Rating findById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * Findet alle Rating-Objekte in der Datenbank.
     * 
     * @return
     */
    public ArrayList<Rating> findAll() {
        // TODO implement here
        return null;
    }

    /**
     * Findet ein Rating-Objekt anhand des übergebenen Bewertungswerts in der Datenbank.
     * 
     * @param score 
     * @return
     */
    public ArrayList<Rating> findByScore(String score) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public static RatingMapper ratingMapper() {
        // TODO implement here
        return null;
    }

}