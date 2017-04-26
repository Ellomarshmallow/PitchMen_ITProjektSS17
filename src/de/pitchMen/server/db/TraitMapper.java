package de.pitchMen.server.db;

/**
 * Bildet Trait-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author
 */

import java.sql.*;
import java.util.Vector;

public class TraitMapper {
	
	    /**
	     * Fügt ein Trait-Objekt der Datenbank hinzu.
	     * 
	     * @param trait 
	     * @return
	     */
	    public Trait insert(Trait trait) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Aktuallisiert ein Trait-Objekt in der Datenbank.
	     * 
	     * @param trait 
	     * @return
	     */
	    public Trait update(Trait trait) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Löscht ein Trait-Objekt aus der Datenbank.
	     * 
	     * @param trait 
	     * @return
	     */
	    public void delete(Trait trait) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Trait-Objekt anhand der übergebenen Id in der Datenbank.
	     * 
	     * @param id 
	     * @return
	     */
	    public Trait findById(int id) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet alle Trait-Objekte in der Datenbank.
	     * 
	     * @return
	     */
	    public ArrayList<Trait> findAll() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Trait-Objekt anhand des übergebenen Namens in der Datenbank.
	     * 
	     * @param name 
	     * @return
	     */
	    public ArrayList<Trait> findByName(String name) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Trait-Objekt anhand des übergebenen Wertes in der Datenbank.
	     * 
	     * @param value 
	     * @return
	     */
	    public ArrayList<Trait> findByValue(String value) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @return
	     */
	    public static TraitMapper tratMapper() {
	        // TODO implement here
	        return null;
	    }

	}