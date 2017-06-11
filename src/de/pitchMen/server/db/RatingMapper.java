package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Rating;

/**
 * Die Klasse RatingMapper bildet Rating-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es
 * möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */

public class RatingMapper {

	/**
	 * Die Klasse RatingMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static RatingMapper ratingMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected RatingMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der RatingMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über RatingMapper.ratingMapper() und
	 * nicht über den New-Operator.
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
	 * Fügt ein Rating-Objekt der Datenbank hinzu.
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
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die
			 * aktuelle id wird um eins erhöht.
			 * Statement ausfüllen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM rating");

			rating.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einfügen des neuen Rating-Tupels in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO rating (id, statement, score, application_id)" 
					+ "VALUES (" + rating.getId()
					+ ", '" + rating.getStatement() + "', " + rating.getScore() + ", " + rating.getApplicationId()
					+ ")");
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
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
			 * SQL-Anweisung zur Aktualisierung des übergebenen Datensatzes in der Datenbank.
			 */	
			stmt.executeUpdate("UPDATE rating SET statement='" + rating.getStatement() + "', score= "
					+ rating.getScore() + " WHERE id= " + rating.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rating;
	}

	/**
	 * Löscht ein Rating-Objekt aus der Datenbank.
	 * 
	 * @param rating
	 */
	public void delete(Rating rating) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Löschen des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM rating WHERE id=" + rating.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Rating-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return rating
	 */
	public Rating findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der übergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score, application_id FROM rating WHERE id=" + id);

			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfragen geprüft, ob es für den angefragten Primärschlüssel
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
			 * SQL-Anweisung zum Finden alle Datensätze in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score, application_id FROM rating ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle raiting vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
	 * Findet ein Rating-Objekt anhand des übergebenen Bewertungswerts in der Datenbank.
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
			 * SQL-Anweisung zum Finden alle Datensätze, anhand des übergebenen score, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, statement, score, application_id FROM rating WHERE score=" + score + " ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle rating vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
	 * Die Methode findRatingByApplicationId sucht anhand der applicationId alle
	 * Rating-Tupel aus der Datenbank und speichert diese in ein Rating-Objekt.
	 * Die Methode ist zur Umsetzung der Anforderung, eine Application zu
	 * löschen, aber bestehende Beziehungen davor zu löschen.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensätze zusammengefügt
	 * werden, zu den es jeweils auch ein Gegenstück in der verknüpften 
	 * Tabelle gibt. Da es möglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * müssen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM rating" + "INNER JOIN application"
					+ "ON application.id = rating.application_id" + "WHERE application.id = " + applicationId);
			/**
			 * 
			 * Zu einer Application besteht maximal ein Rating-Tupel, somit kann
			 * auch nur einer zurückgegeben werden. Mit der IF-Abfrage wird
			 * geprüft, ob ein DB-Tupel vorhanden ist und durch rating-Objekt
			 * zurückgeben, andernfalls wird Null zurückgegeben.
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
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

}
