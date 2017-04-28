package de.pitchMen.server.db;

/**
 * Bildet Marketplace-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 *
 * @author Heike
 *
 */

import java.sql.*;
import java.util.Vector;

import de.pitchMen.shared.bo.Application;

public class ApplicationMapper {

	/**
	 * Die Klasse ApplicationMpper wird nur einmal instantiiert. Die Variable ist mit static gekennzeichnet, da sie die einzige Instanz dieser Klasse speichert.
	 */
	
	private static ApplicationMapper applicationMapper = null;
	
	/**
	 * Ein geschützter Konstrukter verhinder eine neue Instanz dieser Klasse zu erzeugen.
	 */
	
	protected ApplicationMapper() {
	}
	
	/**
	 * Mit Hilfe dieser Methode wird die Singelton-Eigenschaft sichergestellt (es soll nur eine einzige Instanz von ApplicationMapper existieren).
	 */
	
	public static ApplicationMapper applicationMapper() {
		if (applicationMapper == null) {
			applicationMapper = new ApplicationMapper();
		}
		return applicationMapper;
	}
		
	    /**
	     * Fügt ein Application-Objekt der Datenbank hinzu.
	     * 
	     * @param application 
	     * @return
	     */
	
	    public Application insert(Application application) {
	    	Connection con = DBConnection.connection();
	    	
	    	try {
	    		Statement stmt = con.createStatement();
	    		
	    		ResultSet rs = stmt.executeQuery("?");
	    		
	    		if (rs.next()) {
	    			application.setId(rs.getInt("?") + 1);
	    			
	    			stmt = con.createStatement();
	    			
	    			stmt.executeUpdate("?");
	    		}
	    	}
	        catch (SQLException e2) {
	        	e2.printStackTrace();
	        }
	    	
	    	return application;
	    }

	    /**
	     * Aktuallisiert ein Application-Objekt in der Datenbank.
	     * 
	     * @param application 
	     * @return
	     */
	    
	    public Application update(Application application) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	
	        	stmt.executeUpdate("?");
	        	 }
	        catch (SQLException e2) {
	        	e2.printStackTrace();
	        }
	        
	        return application;
	    }

	    /**
	     * Löscht ein Application-Objekt aus der Datenbank.
	     * 
	     * @param application 
	     * @return
	     */
	    
	    public void delete(Application application) {
	    	Connection con = DBCOnnection.connection();
	    	
	    	try {
	    		Statement stmt = con.createStatement();
	    		
	    		stmt.executeUpdate("?");
	    	}
	    	catch (SQLException e2) {
	    		e2.printStackTrace();
	    	}
   	}

	    /**
	     * Findet ein Application-Objekt anhand der übergebenen Id in der Datenbank. Es wird genau ein Objekt zurück gegeben.
	     * 
	     * @param id 
	     * @return
	     */
	    
	    public Application findById(int id) {
	        Connection con = DBConnection.connection();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT id owner FROM application " + "WHERE id =" + id + "ORDER BY owner");
	        	
	        	if (rs.next()) {
	        		Application application = new Application();
	        		application.setId(rs.getInt("id"));
	        		application.setOwnerID(rs.getInt("owner"));
	        		return application;
	        		}
	        	}
	        catch (SQLException e2) {
	        	e2.printStackTrace();
	        	return null;
	        }
	        
	        return null;
	    }

	    /** 
	     * Findet alle JobPosting-Objekte in der Datenbank.
	     * @return
	     */
	    
	    public ArrayList<JobPosting> findAll() {
	        Connection con = DBConnection.connection();
	        
	        ArrayList<JobPosting> result = new ArrayList<JobPosting>();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SOL Statement");
	        while (rs.next()) {
	        	Application application = new Application();
	        	application.setId(rs.getInt("id"));
	        	application.setOwnerID(rs.getInt("owner"));
	        	result.addElement(application);
	        	}
	        }
	        catch (SQLException e2) {
	        	e2.printStackTrace();
	        }	        
	        return result;
	    }
	    

	    /**
	     * Findet ein Application-Objekt anhand des übergebenen Textes in der Datenbank.
	     * 
	     * @param text 
	     * @return
	     */
	    
	    
	    public ArrayList<Application> findByText(String text) {
	    	Connection con = DBConnection.connection();
	        
	        ArrayList<Application> result = new ArrayList<Application>();
	        
	        try {
	        	Statement stmt = con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SOL Statement");
	        while (rs.next()) {
	        	Application application = new Application();
	        	application.setId(rs.getInt("id"));
	        	application.setOwnerID(rs.getInt("owner"));
	        	result.addElement(application);
	        	}
	        }
	        catch (SQLException e2) {
	        	e2.printStackTrace();
	        }	        
	        return result;
	    }
	    

	
}
