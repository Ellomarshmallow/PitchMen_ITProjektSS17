package de.pitchMen.server.db;

/**
 * Bildet JobPosting-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * @author Heike
 *
 */

import java.sql.*;
import java.util.Vector;

public class JobPostingMapper {

	    /**
	     * Fügt ein JobPosting-Objekt der Datenbank hinzu.
	     * 
	     * @param jobPosting 
	     * @return
	     */
	    public JobPosting insert(JobPosting jobPosting) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Aktuallisiert ein JobPosting-Objekt in der Datenbank.
	     * 
	     * @param jobPostingt 
	     * @return
	     */
	    public JobPosting update(JobPosting jobPostingt) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Löscht ein JobPosting-Objekt aus der Datenbank.
	     * 
	     * @param jobPosting 
	     * @return
	     */
	    public void delete(JobPosting jobPosting) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein JobPosting-Objekt anhand der übergebenen Id in der Datenbank.
	     * 
	     * @param id 
	     * @return
	     */
	    public JobPosting findById(int id) {
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
	     * Findet ein JobPosting-Objekt anhand des übergebenen Textes in der Datenbank.
	     * 
	     * @param text 
	     * @return
	     */
	    public ArrayList<JobPosting> findByText(String text) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * Findet ein JobPosting-Objekt anhand des übergebenen Titels in der Datenbank.
	     * 
	     * @param tatle 
	     * @return
	     */
	    public ArrayList<JobPosting> findByTitle(String tatle) {
	        // TODO implement here
	        return null;
	    }

	    /**
	     * @return
	     */
	    public static JobPostingMapper jobPostingMapper() {
	        // TODO implement here
	        return null;
	    }

	
	
}
