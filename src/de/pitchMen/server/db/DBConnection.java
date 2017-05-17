package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Klasse zum Aufbau zur Verbindung der Datenbank.
 * 
 */

public class DBConnection {

	/**
	 * Die Klasse DBCOnnection wird nur einmal instatniiert.
	 */

	private static Connection con = null;

	/**
	 * Mit dieser Url wird die Datenbank angesprochen.
	 */
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String googleUrl = "jdbc:mysql://173.194.105.139:3306/pitchmen_itprojekt_schema";
	   static final String USER = "root";
	   static final String PASS = "winter2017";
	private static String TestUrl = "jdbc:mysql://173.194.105.139:3306/pitchmen_itprojekt_schema";

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
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
					Class.forName("com.mysql.jdbc.Driver");
					url = googleUrl;
				} else {
					/**
					 * Wenn die googleUrl nicht erreichbar ist, wird die LocaleUrl aufgerufen.
					 * Alternativlösung während des Entwickelns.
					 */
					Class.forName("com.mysql.jdbc.Driver");
					url = TestUrl;
				}
				/**
				 * Die Verbindung zur Datenbank wird in der Variablen 
				 * con mit den dazugehörigen Informationen gespeichert
				 */
				con = DriverManager.getConnection(url, USER, PASS);

			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}
		/**
		 * Verbindung wird in der Variable con zurückgegeben
		 */
		return con;
	}

}



