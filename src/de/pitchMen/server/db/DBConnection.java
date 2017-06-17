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

	private static String googleUrl = "jdbc:google:mysql://pitchmen-itprojekt-ss17:pitchmen/pitchmen_itprojekt_schema?user=root&password=winter2017";
	private static String TestUrl = "jdbc:mysql://173.194.105.139:3306/pitchmen_itprojekt_schema?user=root&password=winter2017";

	/**
	 * Stellt die Verbindung zur Datenbank her.
	 * 
	 * @return
	 */

	public static Connection connection() {
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
					 * Alternativl�sung w�hrend des Entwickelns.
					 */
					Class.forName("com.mysql.jdbc.Driver");
					url = TestUrl;
				}
				/**
				 * Die Verbindung zur Datenbank wird in der Variablen 
				 * con mit den dazugeh�rigen Informationen gespeichert
				 */
				con = DriverManager.getConnection(url);

			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}
		/**
		 * Verbindung wird in der Variable con zur�ckgegeben
		 */
		return con;
	}

}



