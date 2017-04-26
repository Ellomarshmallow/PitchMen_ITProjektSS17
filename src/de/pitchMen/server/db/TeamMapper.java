package de.pitchMen.server.db;

/**
 * Bildet Team-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author
 */

import java.sql.*;
import java.util.Vector;

public class TeamMapper {

	    /**
	     * Fügt ein Team-Objekt der Datenbank hinzu.
	     * 
	     * @param team 
	     * @return
	     */
	    public Team insert(Team team) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Aktuallisiert ein Team-Objekt in der Datenbank.
	     * 
	     * @param team 
	     * @return
	     */
	    public Team update(Team team) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Löscht ein Team-Objekt aus der Datenbank.
	     * 
	     * @param team 
	     * @return
	     */
	    public void delete(Team team) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Team-Objekt anhand der übergebenen Id in der Datenbank.
	     * 
	     * @param id 
	     * @return
	     */
	    public Team findById(int id) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet alle Team-Objekte in der Datenbank.
	     * 
	     * @return
	     */
	    public ArrayList<Team> findAll() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Team-Objekt anhand des übergebenen Namens in der Datenbank.
	     * 
	     * @param name 
	     * @return
	     */
	    public ArrayList<Team> findByName(String name) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @return
	     */
	    public static TeamMapper teamMapper() {
	        // TODO implement here
	        return null;
	    }

	
}
