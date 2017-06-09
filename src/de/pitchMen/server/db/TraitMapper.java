package de.pitchMen.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.pitchMen.shared.bo.Trait;

/**
 * Die Klasse TraitMapper bildet Trait-Objekte auf einer relationalen Datenbank
 * ab. Ebenfalls ist es möglich aus den Datenbank-Tupel Java-Objekte zur
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden (insert, search, delete, update).
 * 
 * @author Lars
 */
public class TraitMapper {

	/**
	 * Die Klasse TraitMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft gegeben ist, wird eine
	 * Variable mit dem Schlüsselwort static und dem Standardwert null erzeugt.
	 * Sie speichert die Instanz dieser Klasse.
	 */
	private static TraitMapper traitMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse.
	 */
	protected TraitMapper() {
	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür,
	 * dass nur eine einzige Instanz der TraitMapper-Klasse existiert.
	 * Aufgerufen wird die Klasse somit über TraitMapper.traitMapper() und nicht
	 * über den New-Operator.
	 * 
	 * @return traitMapper
	 */
	public static TraitMapper traitMapper() {
		if (traitMapper == null) {
			traitMapper = new TraitMapper();
		}
		return traitMapper;
	}

	/**
	 * Fügt ein Trait-Objekt der Datenbank hinzu.
	 * 
	 * @param trait
	 * @return trait
	 */
	public Trait insert(Trait trait) {
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM trait");

			trait.setId(rs.getInt("maxid") + 1);
			stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Einfügen des neuen Datensatzes in die Datenbank.
			 */
			stmt.executeUpdate("INSERT INTO trait (id, name, value, partnerProfile_id)" 
					+ "VALUES (" + trait.getId()
					+ ", '" + trait.getName() + "', '" + trait.getValue() + "', " + trait.getPartnerProfileId()
					+ ")");
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return trait;
	}

	/**
	 * Aktualisiert ein Trait-Objekt in der Datenbank.
	 * 
	 * @param trait
	 * @return trait
	 */
	public Trait update(Trait trait) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zur Aktualisierung des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("UPDATE trait SET name='" + trait.getName() + "', value= '" + trait.getValue()
					+ "' WHERE id= " + trait.getId());
		}
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return trait;
	}

	/**
	 * Löscht ein Trait-Objekt aus der Datenbank.
	 * 
	 * @param trait
	 */
	public void delete(Trait trait) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Löschen des übergebenen Datensatzes in der Datenbank.
			 */
			stmt.executeUpdate("DELETE FROM trait WHERE id=" + trait.getId());
		/**
		 * Das Aufrufen des printStackTrace bietet die Möglichkeit, die
		 * Fehlermeldung genauer zu analyisieren. Es werden Informationen dazu
		 * ausgegeben, was passiert ist und wo im Code es passiert ist.
		 */
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Findet ein Trait-Objekt anhand der übergebenen Id in der Datenbank.
	 * 
	 * @param id
	 * @return trait
	 */
	public Trait findById(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, anhand der übergebenen Id, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id, name, value, partnerProfil_id FROM trait WHERE id=" + id);
			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfragen geprüft, ob es für den angefragten Primärschlüssel
			 * ein DB-Tupel gibt.
			 */
			if (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				return trait;
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
	 * Findet alle Trait-Objekte in der Datenbank.
	 * 
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findAll() {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList.
		 */
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden aller Datensatzes in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id AS ID, name as NAME, value AS VALUE, partnerProfil_ID AS PARTNERPROFILID FROM trait ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait vorhanden ist, muss das Abfragen des ResultSet so
			 * oft erfolgen (while-Schleife), bis alle Tupel durchlaufen wurden.
			 * Die DB-Tupel werden in Java-Objekte transformiert und
			 * anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("ID"));
				trait.setName(rs.getString("NAME"));
				trait.setValue(rs.getString("VALUE"));
				trait.setPartnerProfileId(rs.getInt("PARTNERPROFILID"));
				result.add(trait);
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
	 * Findet Trait-Objekte anhand des übergebenen Namens in der Datenbank.
	 * 
	 * @param name
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findByName(String name) {
		Connection con = DBConnection.connection();
		/**
		 * Erzeugen einer ArrayList
		 */
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Namen, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name, value, partnerProfil_id FROM trait WHERE name LIKE '" + name + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem übergebenen Namen vorhanden ist, muss das
			 * Abfragen des ResultSet so oft erfolgen (while-Schleife), bis alle
			 * Tupel durchlaufen wurden. Die DB-Tupel werden in Java-Objekte
			 * transformiert und anschließend der ArrayList hinzugefügt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				result.add(trait);
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
	 * Findet ein Trait-Objekt anhand des übergebenen Wertes(value) in der Datenbank.
	 * 
	 * @param value
	 * @return ArrayList<Trait>
	 */
	public ArrayList<Trait> findByValue(String value) {
		Connection con = DBConnection.connection();
		/**
		* Erzeugen einer ArrayList.
		*/	
		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach dem gesuchten Wert, in der Datenbank, sortiert nach der Id.
			 */
			ResultSet rs = stmt.executeQuery(
					"SELECT id, name, value, partnerProfil_id FROM trait WHERE value LIKE '" + value + "' ORDER BY id");
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem übergebenen Wert vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschließend der ArrayList
			 * hinzugefügt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil_id"));
				result.add(trait);
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
	 * Findet die Eigenschaften passend zur Person-Id, innerhalb der ParterProfile Tabelle. 
	 * Übergibt ein Trait-Objekt zum Vergleich der Traits mit der ArrayList aus der 
	 * Methode @seeFindByJobPosting.
	 * 
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensätze zusammengefügt
	 * werden, zu den es jeweils auch ein Gegenstück in der verknüpften 
	 * Tabelle gibt. Da es möglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * müssen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param id
	 * @return trait
	 */
	public Trait findPartnerProfielByPersonId(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten PersonenId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM partnerProfile "
					+ "INNER JOIN trait ON partnerProfile.id = trait.partnerProfile_id "
					+ "WHERE person_id =" + id);
			/**
			 * Zu einem Primärschlüssel exisitiert nur max ein Datenbank-Tupel,
			 * somit kann auch nur einer zurückgegeben werden. Es wird mit einer
			 * IF-Abfrage geprüft, ob es für den angefragten Primärschlüssel ein
			 * DB-Tupel gibt.
			 */
			if (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfil-id"));
				return trait;
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
	 * Findet alle Eigenschaften (Traits) passend zum ParnterProfile, innerhalb der ParterProfile Tabelle. 
	 * Ausgegeben wird eine ArrayListe, welche die Trait-Objekte beinhaltet
	 *
	 * Mit der Inner-Join-Klausel wird erreicht, dass nur die Datensätze zusammengefügt
	 * werden, zu den es jeweils auch ein Gegenstück in der verknüpften 
	 * Tabelle gibt. Da es möglich ist, dass ein Partnerprofil mehrere Eigenschaften hat,
	 * müssen die PartnerProfile-Objekte in einer ArrayList gespeichert werden.
	 * 
	 * @param partnerProfileId
	 * @return ArrayList<Traits>
	 * 
	 */
	public ArrayList<Trait> findTraitByPartnerProfileId(int partnerProfileId) {
		Connection con = DBConnection.connection();

		ArrayList<Trait> result = new ArrayList<Trait>();

		try {
			Statement stmt = con.createStatement();
			/**
			 * SQL-Anweisung zum Finden des Datensatzes, nach der gesuchten PartnerProfilId, in der Datenbank.
			 */
			ResultSet rs = stmt.executeQuery("SELECT * FROM partnerProfile "
					+ "INNER JOIN trait ON partnerProfile.id = trait.partnerProfile_id "
					+ "WHERE partnerProfileId = " + partnerProfileId);
			/**
			 * Da es sein kann, dass mehr als nur ein Datenbank-Tupel in der
			 * Tabelle trait mit dem übergebenen Wert vorhanden ist,
			 * muss das Abfragen des ResultSet so oft erfolgen (while-Schleife),
			 * bis alle Tupel durchlaufen wurden. Die DB-Tupel werden in
			 * Java-Objekte transformiert und anschließend der ArrayList
			 * hinzugefügt.
			 */
			while (rs.next()) {
				Trait trait = new Trait();
				trait.setId(rs.getInt("id"));
				trait.setName(rs.getString("name"));
				trait.setValue(rs.getString("value"));
				trait.setPartnerProfileId(rs.getInt("partnerProfileId"));
				result.add(trait);
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