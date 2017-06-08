package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Application;
//PartnerProfileID und JobPostingID FK als getter in Application.java implementiert

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
	 * @return application
	 */
	public Application insert(Application application) {
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
				stmt.executeUpdate("INSERT INTO appilcation (id, text, dateCreated, jobPosting_id, partnerProfil_id)"
						+ "VALUES ( " + application.getId() + ", '" + application.getText() + "' ,'"
						+ application.getDateCreated() + "' ,'" + application.getJobPostingId() + "' ,'"
						+ application.getPartnerProfileId() + "')");
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
	 * @return application
	 */
	public Application update(Application application) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE application SET text='" + application.getText() + "', dateCreated= '"
					+ application.getDateCreated() + application.getStatus() + "WHERE id= " + application.getId());
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
	 */
	public void delete(Application application) {
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
	 * @return application
	 */
	public Application findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, text, dateCreated, jobPosting_id, partnerProfil_id, status FROM application "
							+ "WHERE id =" + id);

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
				application.setJobPostingId(rs.getInt("jobPosting_id"));
				application.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				application.setStatus(rs.getString("status"));
				// Methodenaufruf FindByFK von Rating zur Übergaben des
				// Ratingobjekts

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
	 * @return ArrayList<Application>
	 */
	public ArrayList<Application> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, dateCreated, jobPosting_id, partnerProfil_id, status FROM application " + "ORDER BY id");

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
				application.setJobPostingId(rs.getInt("jobPosting_id"));
				application.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				application.setStatus(rs.getString("status"));

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
	 * @return ArryList<Application>
	 */
	public ArrayList<Application> findByText(String text) {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, text, dateCreated, jobPosting_id, partnerProfil_id, status FROM application "
							+ "WHERE text LIKE " + text + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine TUpel zurück gegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.setDateCreated(rs.getDate("dateCreated"));
				application.setJobPostingId(rs.getInt("jobPosting_id"));
				application.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				application.setStatus(rs.getString("status"));

				result.add(application);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Eine JOIN-Klausel wird verwendet, um Zeilen aus zwei oder mehr Tabellen
	 * zu kombinieren, basierend auf einer verwandten Spalte zwischen ihnen.
	 * LEFT JOIN: Gib alle Datensätze aus der linken Tabelle und die
	 * abgestimmten Datensätze aus der rechten Tabelle zurück.
	 * 
	 * @return ArryList<Application>
	 */
	public ArrayList<Application> findAllAsJoin() {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT application.id, application.text, "
					+ "application.dateCreated, application.jobPosting_id, application.partnerProfil_id, application.status "
					+ "rating.id, rating.statement, rating.score "
					+ "FROM application LEFT JOIN rating ON application.id = rating.id " + "ORDER BY application.id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				ArrayList<String> applicationRating = new ArrayList<String>();
				applicationRating.add("application.id");
				applicationRating.add("application.text");
				applicationRating.add("application.dateCreated");
				applicationRating.add("application.jobPosting_id");
				applicationRating.add("application.partnerProfil_id");
				applicationRating.add("application.status");
				applicationRating.add("rating.id");
				applicationRating.add("rating.statement");
				applicationRating.add("rating.score");

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * Bei einer JOIN-Klausel werden Zeilen aus zwei Tabellen zusammengeführt.
	 * Bei dem INNER JOIN verbundenen Tabellen werden nur die Datensätze
	 * übernommen / angezeigt die in beiden Tabellen einen Treffer haben.
	 * Methode u.a. für Aufgabenstellung Nr. 6
	 * 
	 * @return ArryList<Application>
	 */

	public ArrayList<Application> findApplicationByPersonId(int personId) {
		Connection con = DBConnection.connection();
		
		ArrayList<Application> result = new ArrayList<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM application INNER JOIN partnerProfile"
					+ "ON application.partnerProfil_id = partnerProfile.id WHERE partnerProfile.person_id = "
					+ personId);

			/**
			 * Anhand der übergebenen PersonId werden die dazugehörigen
			 * Application-Tupel (Bewerbungen) aus der Datenbank abgefragt.
			 */

			while (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.setDateCreated(rs.getDate("dateCreated"));
				application.setJobPostingId(rs.getInt("jobPosting_id"));
				application.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				application.setStatus(rs.getString("status"));

				result.add(application);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	

	/**
	 * 
	 * Methode ist für das herausfinden, ob Beziehungen zwischen Application und JobPostings bestehen, 
	 * um Applications löschen zu können. Da zu einem JobPosting (Ausschreibung) mehrere Bewerbungen 
	 * bestehen können, muss die Rückgabe in einer ArrayList mit den jeweiligen Application-Objekten, erfolgen. 
	 * 
	 * @param jobPostingId
	 * @return ArryList<Application>
	 */

	public ArrayList<Application> findApplicationByJobPostingId(int jobPostingId) {
		Connection con = DBConnection.connection();
		
		ArrayList<Application> result = new ArrayList<Application>();
		
		/**
		 * Anhand der übergebenen jobPostingId werden die dazugehörigen
		 * Application-Tupel (Bewerbungen) aus der Datenbank abgefragt.
		 */
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM application "
					+ "WHERE jobPosting_id = " + jobPostingId);

		
		/**
		 * Die in dem ResultSet gespeicherten DB-Tupel werden in ein Applicationobjekt 
		 * gespeichert und anschließend das Tupel der ArrayList hinzugefügt.
		 */
			while (rs.next()) {
				Application application = new Application();
				application.setId(rs.getInt("id"));
				application.setText(rs.getString("text"));
				application.setDateCreated(rs.getDate("dateCreated"));
				application.setJobPostingId(rs.getInt("jobPosting_id"));
				application.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				application.setStatus(rs.getString("status"));
				//hinzufügen des Application-Java Objets der ArrayList result
				result.add(application);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
}
