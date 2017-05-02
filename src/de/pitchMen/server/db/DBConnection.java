package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.google.appengine.api.utils.SystemProperty;

/**
 * Klasse zum Aufbau zur Verbindung der Datenbank.
 * 
 * @author Heike, Lars
 */

public class DBConnection {

	/**
	 * Die Klasse DBCOnnection wird nur einmal instatniiert.
	 */

	private static Connection con = null;

	/**
	 * Mit dieser Url wird die Datenbank angesprochen.
	 */
	static final String user = "root";
	static final String pass = "winter2017";
	static final String dbName = "pitchmen_itprojekt_schema";
	static final String googleUrl = "jdbc:google:mysql://173.194.105.139:3306/" + dbName + "?user=" + user
			+ "&password=" + pass + "";
	private static String localUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + "?user=" + user + "&password=" + pass
			+ "";

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
					Class.forName("com.mysql.jdbc.GoogleDriver");
					url = googleUrl;
				} else {
					/**
					 * Wenn die googleUrl nicht erreichbar ist, wird die LocaleUrl aufgerufen.
					 * Alternativlösung während des Entwickelns.
					 */
					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
				}
				/**
				 * Die Verbindung zur Datenbank wird in der Variablen 
				 * con mit den dazugehörigen Informationen gespeichert
				 */
				con = DriverManager.getConnection(url);
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
