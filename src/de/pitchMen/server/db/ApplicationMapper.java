package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Application;
import de.pitchMen.shared.bo.PartnerProfile;
import de.pitchMen.shared.bo.JobPosting;

/**
 * Die Klasse ApplicationMapper bildet Application-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 */

public class ApplicationMapper {

	/**
	 * Die Klasse ApplicationMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Die Variable ist mit static gekennzeichnet, da
	 * sie die einzige Instanz dieser Klasse speichert.
	 */
	private static ApplicationMapper applicationMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */
	protected ApplicationMapper() {
	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der ApplicationMappers
	 * existiert.
	 * 
	 * @return applicationMapper
	 */
	public static ApplicationMapper applicationMapper() {
		if (applicationMapper == null) {
			applicationMapper = new ApplicationMapper();
		}
		return applicationMapper;
	}

	/**
	 * Fügt ein Application-Objekt der Datenbank hinzu. Und gibt das korrigierte
	 * Application-Objekt zurück.
	 * 
	 * @param application
	 * @param jobPosting
	 * @param partnerProfile
	 * @return application
	 * @throws ClassNotFoundException
	 */
	public Application insert(Application application, JobPosting jobPosting, PartnerProfile partnerProfile)
			throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/**
			 * Abfrage des als letztes hinzugefügten Primärschlüssels des
			 * Datensatzes. Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM application");

			if (rs.next()) {
				application.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO appilcation (id, text, dateCreated, jobPosting_id, partnerProfil_id)" + "VALUES ( " + application.getId()
				+ ", '" + application.getText() + "' ,'" + application.getDateCreated() + "' ,'"
				+ jobPosting.getId() + "' ,'" + partnerProfile.getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return application;
	}

	/**
	 * Aktualisiert ein Application-Objekt in der Datenbank.
	 * 
	 * @param application
	 * @throws ClassNotFoundException
	 * @return application
	 */
	public Application update(Application application)
			throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE application SET text='" + application.getText() + "', dateCreated= '"
					+ application.getDateCreated() + "WHERE id= " + application.getId());
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
	 * @throws ClassNotFoundException
	 */
	public void delete(Application application) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM application " + "WHERE id=" + application.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Application-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @return application
	 */
	public Application findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, dateCreated FROM application " + "WHERE id =" + id);

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			if (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.setDateCreated(rs.getDate("dateCreated"));
//Methodenaufruf FindByFK von Rating zur Übergaben des Ratingobjekts			
				
				return application;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Findet alle Application-Objekte in der Datenbank.
	 * 
	 * @throws ClassNotFoundException
	 * @return ArrayList<Application>
	 */
	public ArrayList<Application> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, dateCreated FROM application " + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.setDateCreated(rs.getDate("dateCreated"));

				result.add(application);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Application-Objekt anhand des übergebenen Namens in der
	 * Datenbank.
	 * 
	 * @param name
	 * @throws ClassNotFoundException
	 * @return ArryList<Application>
	 */
	public ArrayList<Application> findByText(String text) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, dateCreated FROM application " + "WHERE text LIKE " + text + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine TUpel zurück gegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.getDateCreated();
				application.getRating();

				result.add(application);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
