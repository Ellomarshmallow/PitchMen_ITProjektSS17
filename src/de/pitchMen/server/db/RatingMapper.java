package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.pitchMen.shared.bo.Rating;

/**
 * Bildet Rating-Objekte auf eine relationale Datenbank ab. Ebenfalls ist es
 * möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
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
	 * @return trait
	 */
	public Rating insert(Rating rating) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die
			 * aktuelle id wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM rating");

			rating.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einfügen des neuen Rating-Tupels in die
			 * Datenbank
			 */
			stmt.executeUpdate("INSERT INTO rating (id, statement, score)" + "VALUES (" + rating.getId() + ", '"
					+ rating.getStatement() + "', '" + rating.getScore() + "')");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return rating;
	}

	/**
	 * Aktualisiert ein Rating-Objekt in der Datenbank.
	 * 
	 * @param rating
	 * @throws ClassNotFoundException
	 * @return rating
	 */
	public Rating update(Rating rating) throws ClassNotFoundException {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE rating SET statement='" + rating.getStatement() + "', score= '"
					+ rating.getScore() + "' WHERE id= " + rating.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return rating;
	}

	/**
	 * Löscht ein Rating-Objekt aus der Datenbank.
	 * 
	 * @param rating
	 * @throws ClassNotFoundException
	 */
	public void delete(Rating rating) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
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
	 * 
	 */
	public Rating findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score FROM rating WHERE id=" + id);

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
				return rating;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle Rating-Objekte in der Datenbank.
	 * 
	 * @return result
	 */
	public ArrayList<Rating> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Rating> result = new ArrayList<Rating>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, statement, score FROM rating ORDER BY id");

			while (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));

				result.add(rating);

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Rating-Objekt anhand des übergebenen Bewertungswerts in der
	 * Datenbank.
	 * 
	 * @param score
	 * @return
	 */
	public ArrayList<Rating> findByScore(float score) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<Rating> result = new ArrayList<Rating>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, statement, score FROM rating WHERE score=" + score + " ORDER BY id");

			while (rs.next()) {
				Rating rating = new Rating();
				rating.setId(rs.getInt("id"));
				rating.setStatement(rs.getString("statement"));
				rating.setScore(rs.getFloat("score"));

				result.add(rating);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

}
