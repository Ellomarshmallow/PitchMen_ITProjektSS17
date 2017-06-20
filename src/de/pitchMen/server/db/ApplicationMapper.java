package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Application;

/**
 * Die Klasse ApplicationMapper bildet Application-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 * 
 * @author Heike
 */
public class ApplicationMapper {

	/**
	 * Die Klasse ApplicationMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static ApplicationMapper applicationMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */
	protected ApplicationMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der ApplicationMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über ApplicationMapper.applicationMapper() 
	 * und nicht über den New-Operator.
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
	 * Fügt ein Application-Objekt der Datenbank hinzu. 
	 * 
	 * @param application
	 * @return application
	 */
	public Application insert(Application application) {
		/**
		 *  DB-Verbindung holen.
		 */
		Connection con = DBConnection.connection();

		try {
			/**
			 * leeres SQL-Statement (JDBC) anlegen.
			 */		
			Statement stmt = con.createStatement();

			/**
			 * Abfrage des als letztes hinzugefügten Primärschlüssels (id) Datensatzes. 
			 * Die aktuelle id wird um eins erhöht.
			 * Statement ausfüllen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM application");
			if(rs.next()) {
				application.setId(rs.getInt("maxid") + 1);
			}
			stmt = con.createStatement();
				/**
				 * SQL-Anweisung zum Einfügen des neuen Datensatzes in die Datenbank.
				 */
				stmt.executeUpdate("INSERT INTO appilcation (id, text, dateCreated, jobPosting_id, partnerProfil_id)"
						+ "VALUES ( " + application.getId() + ", '" + application.getText() + "' ,'"
						+ application.getDateCreated() + "' ," + application.getJobPostingId() + " ,'"
						+ application.getPartnerProfileId() + ")");
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
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
			/**
			 * SQL-Anweisung zur Aktualisierung des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("UPDATE application SET text='" + application.getText() + "', dateCreated= '"
					+ application.getDateCreated() + application.getStatus() + "WHERE id= " + application.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
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
			/**
			 * SQL-Anweisung zum Löschen des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM application " + "WHERE id=" + application.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
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
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der übergebenen Id, in der Datenbank.
			 */
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
				return application;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
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
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, dateCreated, jobPosting_id, partnerProfil_id, status FROM application " + "ORDER BY id");

			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle application vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Application-Objekt anhand des übergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArryList<Application>
	 */
	public ArrayList<Application> findByText(String text) {
		Connection con = DBConnection.connection();

		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Namen, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, text, dateCreated, jobPosting_id, partnerProfil_id, status FROM application "
							+ "WHERE text LIKE " + text + "ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle application vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	/**
	 * Findet alle Bewerbungen passend zur ratngId (Bewertung), innerhalb der application Tabelle. 
	 * Übergibt ein application-Objekt.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensätze zusammengefügt
	 * werden, zu den es jeweils auch ein Gegenstück in der verknüpften 
	 * Tabelle gibt. Da es möglich ist, dass eine Bewerbung mehrere Bewertungen hat,
	 * müssen die application-Objekte in einer ArrayList gespeichert werden.
	 * 	
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
					+ "application.dateCreated, application.jobPosting_id, application.partnerProfil_id, "
					+ "application.status, rating.id, rating.statement, rating.score "
					+ "FROM application LEFT JOIN rating ON application.id = rating.id " + "ORDER BY application.id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle application vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet die Bewerbungen passend zur Person-Id, innerhalb der applicaton-Tabelle. 
	 * Übergibt ein application-Objekt.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensätze zusammengefügt
	 * werden, zu den es jeweils auch ein Gegenstück in der verknüpften 
	 * Tabelle gibt. Da es möglich ist, dass eine Person mehrere Bewerbungen hat,
	 * müssen die Bewerbungs-Objekte in einer ArrayList gespeichert werden.
	 *
	 * @param personId
	 * @return ArryList<Application>
	 */
	public ArrayList<Application> findApplicationsByPersonId(int personId) {
		Connection con = DBConnection.connection();
		
		ArrayList<Application> result = new ArrayList<Application>();
		
		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden der Datensätze, nach der gesuchten PersonenId, in der Datenbank.
			 */			
			ResultSet rs = stmt.executeQuery("SELECT * FROM application INNER JOIN partnerProfile"
					+ " ON application.partnerProfil_id = partnerProfile.id WHERE partnerProfile.person_id = "
					+ personId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle application vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
			e2.getCause();
			e2.getErrorCode();
			e2.getLocalizedMessage();

			}

		return result;

	}
	
	/**
	 * Methode ist für das herausfinden, ob Beziehungen zwischen Application und JobPostings bestehen, 
	 * um Applications löschen zu können. Da zu einem JobPosting (Ausschreibung) mehrere Bewerbungen 
	 * bestehen können, muss die Rückgabe in einer ArrayList mit den jeweiligen Application-Objekten, erfolgen. 
	 * 
	 * @param jobPostingId
	 * @return ArryList<Application>
	 */

	public ArrayList<Application> findApplicationsByJobPostingId(int jobPostingId) {
		Connection con = DBConnection.connection();
		
		ArrayList<Application> result = new ArrayList<Application>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung - Anhand der übergebenen jobPostingId werden die dazugehörigen
			 * Application-Tupel (Bewerbungen) aus der Datenbank abgefragt.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM application "
					+ "WHERE jobPosting_id = " + jobPostingId);

			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle application vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
}
