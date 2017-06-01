package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Marketplace;
//CompanyID FK, PersonID FK und TeamID FK als getter in Marketplace.java implementiert

/**
 * Die Klasse MarketplaceMapper bildet Marketplace-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (Speichern, Suchen, Löschen, Bearbeiten).
 * 
 * @author Heike
 *
 */
public class MarketplaceMapper {

	/**
	 * Die Klasse MarketplaceMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Die Variable ist mit static gekennzeichnet, da
	 * sie die einzige Instanz dieser Klasse speichert.
	 */

	private static MarketplaceMapper marketplaceMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu
	 * erzeugen.
	 */

	protected MarketplaceMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Es wird somit
	 * sichergestellt, dass nur eine einzige Instanz der CompanyMapper
	 * existiert.
	 * 
	 * @return marketplaceMapper
	 */

	public static MarketplaceMapper marketplaceMapper() {
		if (marketplaceMapper == null) {
			marketplaceMapper = new MarketplaceMapper();
		}
		return marketplaceMapper;
	}

	/**
	 * Fügt ein Marketplace-Objekt der Datenbank hinzu. Und gibt das korrigierte
	 * Marketplace-Objekt zurück.
	 * 
	 * @param marketplace
	 * @return marketplace
	 * @throws ClassNotFoundException
	 */
	public Marketplace insert(Marketplace marketplace) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/**
			 * Abfrage des als letztes hinzugefügten Primärschlüssels des
			 * Datensatzes. Der aktuelle Primärschlüssel wird um eins erhöht.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM marketplace");
			if (rs.next()) {
				marketplace.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();

				/**
				 * Ausführen der Einfügeoperation
				 */
				stmt.executeUpdate("INSERT INTO marketplace (id, description, title, person_id, team_id, company_id)"
						+ "VALUES ( " + marketplace.getId() + ", '" + marketplace.getDescription() + "' ,'"
						+ marketplace.getTitle() + "' ,'" + marketplace.getPersonId() + "' ,'" + marketplace.getTeamId()
						+ "' ,'" + marketplace.getCompanyId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return marketplace;
	}

	/**
	 * Aktuallisiert ein Marketplace-Objekt in der Datenbank.
	 * 
	 * @param marketplace
	 * @return marketplace
	 */
	public Marketplace update(Marketplace marketplace) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE marketplace SET description= '" + marketplace.getDescription() + "', "
					+ "title= '" + marketplace.getTitle() + "' " + "WHERE id=" + marketplace.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return marketplace;
	}

	/**
	 * Löscht ein Marketplace-Objekt aus der Datenbank.
	 * 
	 * @param marketplace
	 */
	public void delete(Marketplace marketplace) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM marketplace " + "WHERE id=" + marketplace.getId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Marketplace-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return marketplace
	 */
	public Marketplace findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, description, title, person_id, team_id, company_id FROM marketplace "
							+ "WHERE id=" + id);

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			if (rs.next()) {
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				marketplace.setCompanyId(rs.getInt("company_id"));
				marketplace.setPersonId(rs.getInt("person_id"));
				marketplace.setTeamId(rs.getInt("team_id"));

				return marketplace;
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	/**
	 * Findet alle Marketplace-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<marketplace>
	 */
	public ArrayList<Marketplace> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Marketplace> result = new ArrayList<Marketplace>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, description, title, person_id, team_id, company_id FROM marketplace " + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Es
			 * wird geprüft ob ein Ergebnis vorliegt Das Ergebnis-Tupel wird in
			 * ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				marketplace.setCompanyId(rs.getInt("company_id"));
				marketplace.setPersonId(rs.getInt("person_id"));
				marketplace.setTeamId(rs.getInt("team_id"));

				result.add(marketplace);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Findet ein Marketplace-Objekt anhand des übergebenen Titels in der
	 * Datenbank.
	 * 
	 * @param title
	 * @return ArrayList<Marketplace>
	 */
	public ArrayList<Marketplace> findByTitle(String title) {
		Connection con = DBConnection.connection();

		ArrayList<Marketplace> result = new ArrayList<Marketplace>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, description, title, person_id, team_id, company_id FROM marketplace "
							+ "WHERE title LIKE " + title + "ORDER BY id");

			/**
			 * Der Primärschlüssel (id) wird als eine Tupel zurückgegeben. Das
			 * Ergebnis-Tupel wird in ein Objekt umgewandelt.
			 * 
			 */
			while (rs.next()) {
				Marketplace marketplace = new Marketplace();
				marketplace.setId(rs.getInt("id"));
				marketplace.setDescription(rs.getString("description"));
				marketplace.setTitle(rs.getString("title"));
				marketplace.setCompanyId(rs.getInt("company_id"));
				marketplace.setPersonId(rs.getInt("person_id"));
				marketplace.setTeamId(rs.getInt("team_id"));

				result.add(marketplace);
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

}
