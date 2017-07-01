package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Rating;

/**
 * Die Klasse RatingMapper bildet Rating-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es
 * moeglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */

public class RatingMapper {

	/**
	 * Die Klasse RatingMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfuellt werden kann,
	 * wird zunaechst eine Variable mit dem Schluesselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static RatingMapper ratingMapper = null;

	/**
	 * Ein geschuetzter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected RatingMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt dafuer,
	 * dass nur eine einzige Instanz der RatingMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit ueber RatingMapper.ratingMapper() und
	 * nicht ueber den New-Operator.
	 * 
	 * @return ratingMapper
	 */
	public static RatingMapper ratingMapper() {
		if (ratingMapper == null) {
			ratingMapper = new RatingMapper();
		}
		return ratingMapper;
	}

	/**
	 * Fuegt ein Rating-Objekt der Datenbank hinzu.
	 * 
	 * @param rating
	 * @param application
	 * @return trait
	 */
	public Rating insert(Rating rating) {
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
			 * Abfrage des zuletzt hinzugefuegten Primaerschluessels (id). Die
			 * aktuelle id wird um eins erhoeht.
			 * Statement ausfuellen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM rating");

			if(rs.next()) {
				rating.setId(rs.getInt("maxid") + 1);
			}
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einfuegen des neuen Rating-Tupels in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO rating (id, statement, score, application_id)" 
					+ "VALUES (" + rating.getId()
					+ ", '" + rating.getStatement() + "', " + rating.getScore() + ", " + rating.getApplicationId()
					+ ")");
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rating;
	}

	/**
	 * Aktualisiert ein Rating-Objekt in der Datenbank.
	 * 
	 * @param rating
	 * @return rating
	 */
	public Rating update(Rating rating) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zur Aktualisierung des uebergebenen Datensatzes in der Datenbank.
			 */	
			stmt.executeUpdate("UPDATE rating SET statement='" + rating.getStatement() + "', score= "
					+ rating.getScore() + " WHERE id= " + rating.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rating;
	}

	/**
	 * L�scht ein Rating-Objekt aus der Datenbank.
	 * 
	 * @param rating
	 */
	public void delete(Rating rating) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Loeschen des uebergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM rating WHERE id=" + rating.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Rating-Objekt anhand der uebergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return rating
	 */
	public Rating findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der uebergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score, application_id FROM rating WHERE id=" + id);

			/**
			 * Zu einem Primaerschluessel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurueckgegeben werden. Es wird mit einer
			 * IF-Abfragen geprueft, ob es fuer den angefragten Primaerschluessel
			 * ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));
				rating.setApplicationId(rs.getInt("application_id"));
				return rating;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle Rating-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Rating>
	 */
	public ArrayList<Rating> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Rating> result = new ArrayList<Rating>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden alle Datensaetze in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score, application_id FROM rating ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle raiting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));
				rating.setApplicationId(rs.getInt("application_id"));
				result.add(rating);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Rating-Objekt anhand des uebergebenen Bewertungswerts in der Datenbank.
	 * 
	 * @param score
	 * @return ArrayList<Rating>
	 */
	public ArrayList<Rating> findByScore(float score) {
		Connection con = DBConnection.connection();

		ArrayList<Rating> result = new ArrayList<Rating>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden alle Datensaetze, anhand des uebergebenen score, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, statement, score, application_id FROM rating WHERE score=" + score + " ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle rating vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschliessend der ArrayList hinzugefuegt.
			 */
			while (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));
				rating.setApplicationId(rs.getInt("application_id"));
				result.add(rating);
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Die Methode findRatingByApplicationId sucht anhand der applicationId alle
	 * Rating-Tupel aus der Datenbank und speichert diese in ein Rating-Objekt.
	 * Die Methode ist zur Umsetzung der Anforderung, eine Application zu
	 * loeschen, aber bestehende Beziehungen davor zu loeschen.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensaetze zusammengefuegt
	 * werden, zu den es jeweils auch ein Gegenstueck in der verkn�pften 
	 * Tabelle gibt. Da es moeglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * muessen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param applicationId
	 * @return Rating
	 */
	public Rating findRatingByApplicationId(int applicationId) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten applicationId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM rating " + "INNER JOIN application "
					+ "ON application.id = rating.application_id " + "WHERE application.id = " + applicationId);
			/**
			 * 
			 * Zu einer Application besteht maximal ein Rating-Tupel, somit kann
			 * auch nur einer zurueckgegeben werden. Mit der IF-Abfrage wird
			 * geprueft, ob ein DB-Tupel vorhanden ist und durch rating-Objekt
			 * zurueckgeben, andernfalls wird Null zurueckgegeben.
			 */
			if (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));
				rating.setApplicationId(rs.getInt("application_id"));
				return rating;
			}
			/**
			 * Das Aufrufen des printStackTrace bietet die Moeglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

}
