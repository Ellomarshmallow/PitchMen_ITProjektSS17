package de.pitchMen.server.db;

/**
 * @author Heike
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	static final String jdbc_driver = "com.mysql.jdbc.Driver";
	/**
	 * Mit dieser Url wird die Datenbank angesprochen.
	 */
	//static final String user = "root";
	//static final String pass = "";
	static final String googleUrl = "jdbc:google:mysql://173.194.105.139:3306/pitchmen_itprojekt_schema?user=root&password=";


	/**
	 * Stellt die Verbindung zur Datenbank her.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */

	public static Connection connection() throws ClassNotFoundException {

		/*
		 * Herstellung einer DB Verbindung, wenn bisher keine Verbindung besteht
		 */
		if (con == null) {
			String url = null;
			try {
				Class.forName(jdbc_driver);
				url = googleUrl;

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}

		return con;
	}

}
