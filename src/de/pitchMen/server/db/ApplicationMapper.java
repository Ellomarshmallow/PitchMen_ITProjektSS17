package de.pitchMen.server.db;

/**
 * Bildet Marketplace-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 *
 * @author Heike
 *
 */

import java.sql.*;
import java.util.Vector;


public class ApplicationMapper {

	    /**
	     * Fügt ein Application-Objekt der Datenbank hinzu.
	     * 
	     * @param application 
	     * @return
	     */
	    public Application insert(Application application) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Aktuallisiert ein Application-Objekt in der Datenbank.
	     * 
	     * @param application 
	     * @return
	     */
	    public Application update(Application application) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Löscht ein Application-Objekt aus der Datenbank.
	     * 
	     * @param application 
	     * @return
	     */
	    public void delete(Application application) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Application-Objekt anhand der übergebenen Id in der Datenbank.
	     * 
	     * @param id 
	     * @return
	     */
	    public Application findById(int id) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet alle JobPosting-Objekte in der Datenbank.
	     * 
	     * @return
	     */
	    public ArrayList<JobPosting> findAll() {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein Application-Objekt anhand des übergebenen Textes in der Datenbank.
	     * 
	     * @param text 
	     * @return
	     */
	    public ArrayList<Application> findByText(String text) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @return
	     */
	    public static ApplicationMapper applicationMapper() {
	        // TODO implement here
	        return null;
	    }

	
}
