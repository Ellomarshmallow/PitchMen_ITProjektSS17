package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;
import de.pitchMen.shared.bo.PartnerProfile;


/**
 * Bildet PartnerProfile-Objekte auf eine relationale Datenbank ab. Ebenfalls
 * ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * @author
 */

public class PartnerProfileMapper {

	/**
	 * Die Klasse PartnerProfileMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann,
	 * wird zunächst eine Variable mit dem Schlüsselwort static und dem
	 * Standardwert null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static PartnerProfileMapper partnerProfileMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */

	protected PartnerProfileMapper() {

	}

	/**
	 * Methode zum sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der PartnerProfileMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über
	 * PartnerProfileMapper.partnerProfileMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return partnerProfileMapper
	 */

	public static PartnerProfileMapper partnerProfileMapper() {
		if (partnerProfileMapper == null) {
			partnerProfileMapper = new PartnerProfileMapper();
		}
		return partnerProfileMapper;

	}

	/**
	 * Fügt ein PartnerProfile-Objekt der Datenbank hinzu.
	 * 
	 * @param partnerProfile
	 * @throws ClassNotFoundException
	 * @return partnerProfile
	 */
	public PartnerProfile insert(PartnerProfile partnerProfile) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die
			 * aktuelle id wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM partnerProfile");

			partnerProfile.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();

			/**
			 * SQL-Anweisung zum Einfügen des neuen PartnerProfile-Tupels in die
			 * Datenbank
			 */
			stmt.executeUpdate("INSERT INTO partnerProfile (id, dateCreated, dateChanged) VALUES ("
					+ partnerProfile.getId() + ", '" + partnerProfile.getDateCreated() + "', '"
					+ partnerProfile.getDateChanged() + "')");

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return partnerProfile;
	}

	/**
	 * Aktualisiert ein PartnerProfile-Objekt in der Datenbank.
	 * 
	 * @param partnerProfile
	 * @return partnerProfile
	 * @throws ClassNotFoundException
	 */
	public PartnerProfile update(PartnerProfile partnerProfile) throws ClassNotFoundException {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE partnerProfile SET dateCreated='" + partnerProfile.getDateCreated()
					+ "', dateChanged= '" + partnerProfile.getDateChanged() + "' WHERE id= " + partnerProfile.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return partnerProfile;
	}

	/**
	 * Löscht ein PartnerProfile-Objekt aus der Datenbank.
	 * 
	 * @param partnerProfile
	 * @throws ClassNotFoundException
	 */
	public void delete(PartnerProfile partnerProfile) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM partnerProfile WHERE id=" + partnerProfile.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Findet ein PartnerProfile-Objekt anhand der übergebenen Id in der
	 * Datenbank.
	 * 
	 * @param id
	 * @throws ClassNotFoundException
	 * @return null
	 */
	public PartnerProfile findById(int id) throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, dateCreated, dateChanged FROM partnerProfile WHERE id=" + id);

			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfrage geprüft, ob es für den angefragten Primärschlüssel ein
			 * DB-Tupel gibt.
			 */

			if (rs.next()) {
				PartnerProfile partnerProfile = new PartnerProfile();
				partnerProfile.setId(rs.getInt("id"));
				partnerProfile.setDateCreated(rs.getDate("dateCreated"));
				partnerProfile.setDateChanged(rs.getDate("dateChanged"));
				return partnerProfile;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	/**
	 * Findet alle PartnerProfile-Objekte in der Datenbank.
	 * 
	 * @return result
	 * @throws ClassNotFoundException
	 */
	public ArrayList<PartnerProfile> findAll() throws ClassNotFoundException {
		Connection con = DBConnection.connection();

		ArrayList<PartnerProfile> result = new ArrayList<PartnerProfile>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, dateCreated, dateChanged FROM partnerProfile ORDER BY id");

			if (rs.next()) {
				PartnerProfile partnerProfile = new PartnerProfile();
				partnerProfile.setId(rs.getInt("id"));
				partnerProfile.setDateCreated(rs.getDate("dateCreated"));
				partnerProfile.setDateChanged(rs.getDate("dateChanged"));
				result.add(partnerProfile);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
