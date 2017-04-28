package de.pitchMen.server.db;

/**
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
	     * Die Klasse DBCOnnection wird nur einmal instatniiert. 
	     */
	
	    private static Connection con = null;

	    /**
	     *  Mit dieser Url wird die Datenbank angesprochen.
	     */
	    
	    private static String localUrl;
	    private static String googleUrl;

	    /**
	     * Stellt die Verbindung zur Datenbank her. 
	     * 
	     * @return
	     */
	    	    
	    public static Connection connection() {
	    	/**
	    	 * Herstellung einer DB Verbindung, wenn bisher keine Verbindung besteht
	    	 * 
	    	 * if (con == null) {
	    	 * 	String url = null;
	    	 *  try {
	    	 *  	if (?
	    	 *  		)
	    	 *  	} else {
	    	 *  		?
	    	 *  	}
	    	 *  }
	    	 *  con = DriverManager.getConnection(url);
	    	 *  } catch (Exception e) {
	    	 *  	con = null;
	    	 *  	e.printStackTrace();
	    	 *  }
	    	 *  }
	    	 */

	    return con;
	    }

}
	

