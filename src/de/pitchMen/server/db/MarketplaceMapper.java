package de.pitchMen.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Marketplace;

/**
 * Die Klasse MarketplaceMapper bildet Marketplace-Objekte auf einer relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Heike
 */
public class MarketplaceMapper {

	/**
	 * Die Klasse MarketplaceMapper wird nur einmal instantiiert
	 * (Singelton-Eigenschaft). Damit diese Eigenschaft gegeben ist, wird eine
	 * Variable mit dem Schlüsselwort static und dem Standardwert null erzeugt.
	 * Sie speichert die Instanz dieser Klasse.
	 */
	private static MarketplaceMapper marketplaceMapper = null;

	/**
	 * Ein geschützter Konstrukter verhindert eine neue Instanz dieser Klasse zu erzeugen.
	 */
	protected MarketplaceMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der MarketplaceMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über MarketplaceMapper.marketplaceMapper() und nicht
	 * über den New-Operator.
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
	 * Fügt ein Marketplace-Objekt der Datenbank hinzu
	 * 
	 * @param marketplace
	 * @return marketplace
	 */
	public Marketplace insert(Marketplace marketplace) {
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
			 * Abfrage des zuletzt hinzugefügten Primärschlüssels (id). Die aktuelle id wird um eins erhöht.
			 * Statement ausfüllen und als Query an die Datenbank senden.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM marketplace");

			if(rs.next()) {
				marketplace.setId(rs.getInt("maxid") + 1);
				}
			stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Einfügen des neuen Datensatzes in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO marketplace (id, description, title, person_id, team_id, company_id)"
						+ "VALUES ( " + marketplace.getId() + ", '" + marketplace.getDescription() + "' ,'"
						+ marketplace.getTitle() + "' ," + marketplace.getPersonId() + " ," + marketplace.getTeamId()
						+ " ," + marketplace.getCompanyId() + ")");
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
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
			/**
			 * SQL-Anweisung zum Aktualisieren des Datensatzes in die Datenbank.
			 */
			stmt.executeUpdate("UPDATE marketplace SET description= '" + marketplace.getDescription() + "', "
					+ "title= '" + marketplace.getTitle() + "' " + "WHERE id=" + marketplace.getId());
			/**
			 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
			 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
			 * ausgegeben, was passiert ist und wo im Code es passiert ist.
			 */	
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
			/**
			 * SQL-Anweisung zum Löschen des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM marketplace " + "WHERE id=" + marketplace.getId());
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
	 * Findet ein Marketplace-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return marketplace
	 */
	public Marketplace findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der übergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, description, title, person_id, team_id, company_id FROM marketplace "
							+ "WHERE id=" + id);
			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfragen geprüft, ob es für den angefragten Primärschlüssel
			 * ein DB-Tupel gibt.
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
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
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
		/**
		 * Erzeugen einer ArrayList.
		 */
		ArrayList<Marketplace> result = new ArrayList<Marketplace>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, description, title, person_id, team_id, company_id FROM marketplace " 
							+ "ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle marketplace vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Findet ein Marketplace-Objekt anhand des übergebenen Titels in der Datenbank.
	 * 
	 * @param title
	 * @return ArrayList<Marketplace>
	 */
	public ArrayList<Marketplace> findByTitle(String title) {
		Connection con = DBConnection.connection();

		ArrayList<Marketplace> result = new ArrayList<Marketplace>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Titel, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id, description, title, person_id, team_id, company_id FROM marketplace "
							+ "WHERE title LIKE '" + title + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle marketplace vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
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
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */	
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
