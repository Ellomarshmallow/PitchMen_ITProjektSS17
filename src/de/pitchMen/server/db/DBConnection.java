package de.pitchMen.server.db;

/**
 * muss manuell importiert werden
 * @author Heike
 */

import java.sql.Connection;
import java.sql.DriverManager;
import com.google.appengine.api.utils.SystemProperty;

/**
 * Klasse zum Aufbau zur Verbindung der Datenbank.
 * 
 * @author
 */

public class DBConnection {

	    /**
	     * 
	     */
	    private static Connection con;

	    /**
	     * 
	     */
	    private static String localUrl;

	    /**
	     * 
	     */
	    private static String googleUrl;

	    /**
	     * Stellt die Verbindung zur Datenbank her. 
	     * 
	     * @return
	     */
	    public static Connection connection() {
	        // TODO implement here
	        return null;
	    }

}
	

