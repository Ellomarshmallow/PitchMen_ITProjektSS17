package de.pitchMen.server.db;


import java.sql.*;
import java.util.Vector;

/**
 * Bildet Person-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author
 */

public class PersonMapper {


    /**
     * Fügt ein Person-Objekt der Datenbank hinzu.
     * 
     * @param person 
     * @return
     */
    public Person insert(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * Aktuallisiert ein Person-Objekt in der Datenbank.
     * 
     * @param person 
     * @return
     */
    public Person update(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * Löscht ein Person-Objekt aus der Datenbank.
     * 
     * @param person 
     * @return
     */
    public void delete(Person person) {
        // TODO implement here
        return null;
    }

    /**
     * Findet ein Person-Objekt anhand der übergebenen Id in der Datenbank.
     * 
     * @param id 
     * @return
     */
    public Person findById(int id) {
        // TODO implement here
        return null;
    }

    /**
     * Findet alle Person-Objekte in der Datenbank.
     * 
     * @return
     */
    public ArrayList<Person> findAll() {
        // TODO implement here
        return null;
    }

    /**
     * Findet ein Person-Objekt anhand des übergebenen Namens in der Datenbank.
     * 
     * @param name 
     * @return
     */
    public ArrayList<Person> findByName(String name) {
        // TODO implement here
        return null;
    }

    /**
     * Findet ein Person-Objekt anhand des übergebenen Vornamens in der Datenbank.
     * 
     * @param firstName 
     * @return
     */
    public ArrayList<Person> findByFirstName(String firstName) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public static PersonMapper personMapper() {
        // TODO implement here
        return null;
    }

}